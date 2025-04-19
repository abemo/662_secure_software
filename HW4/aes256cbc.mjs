import { Buffer } from "buffer";
import { createCipheriv, createDecipheriv, randomBytes } from "crypto";

function encrypt(message, key, iv) {
  const cipher = createCipheriv("aes-256-cbc", Buffer.from(key, "utf8"), Buffer.from(iv, "utf8"));
  let encrypted = cipher.update(message, "utf8", "hex");
  encrypted += cipher.final("hex");
  return encrypted;
}

function decrypt(encrypted, key, iv) {
  const decipher = createDecipheriv("aes-256-cbc", Buffer.from(key, "utf8"), Buffer.from(iv, "utf8"));
  let decrypted = decipher.update(encrypted, "hex", "utf8");
  decrypted += decipher.final("utf8");
  return decrypted;
}

const args = process.argv.slice(2);

if (args[0] === "-k" && args.length === 1) {
  console.log("Generated key:", randomBytes(32).toString("utf8"));
  process.exit(0);
}

if (args[0] === "-iv" && args.length === 1) {
  console.log("Generated IV:", randomBytes(16).toString("utf8"));
  process.exit(0);
}

if (args.length !== 4) {
  console.error("Usage: node aes256cbc.mjs <-e|-d> <message|ciphertext> <key> <iv>");
  console.error("       node aes256cbc.mjs -k");
  console.error("       node aes256cbc.mjs -iv");
  process.exit(1);
}

const [mode, input, key, iv] = args;

if (Buffer.from(key, "utf8").length !== 32) {
  console.error("Error: Key must be exactly 32 bytes long.");
  process.exit(1);
}

if (Buffer.from(iv, "utf8").length !== 16) {
  console.error("Error: Initialization vector (IV) must be exactly 16 characters long.");
  process.exit(1);
}

try {
  if (mode === "-e") {
    console.log("Encrypted message:", encrypt(input, key, iv));
  } else if (mode === "-d") {
    console.log("Decrypted message:", decrypt(input, key, iv));
  } else {
    console.error("Invalid mode. Use -e to encrypt, -d to decrypt, -k to generate a key, or -iv to generate an IV.");
    process.exit(1);
  }
} catch (error) {
  console.error("Error:", error.message);
  process.exit(1);
}
