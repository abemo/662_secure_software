import { Buffer } from "buffer";
import { createCipheriv, createDecipheriv, randomBytes } from "crypto";

function encrypt(message, key, iv) {
  const cipher = createCipheriv("aes-256-cbc", Buffer.from(key, "hex"), Buffer.from(iv, "hex"));
  let encrypted = cipher.update(message, "utf8", "hex");
  encrypted += cipher.final("hex");
  return encrypted;
}

function decrypt(encrypted, key, iv) {
  const decipher = createDecipheriv("aes-256-cbc", Buffer.from(key, "hex"), Buffer.from(iv, "hex"));
  let decrypted = decipher.update(encrypted, "hex", "utf8");
  decrypted += decipher.final("utf8");
  return decrypted;
}

const args = process.argv.slice(2);

if (args[0] === "-k" && args.length === 1) {
  console.log("Generated key:", randomBytes(32).toString("hex"));
  process.exit(0);
}

if (args[0] === "-iv" && args.length === 1) {
  console.log("Generated IV:", randomBytes(16).toString("hex"));
  process.exit(0);
}

if (args.length !== 4) {
  console.error("Usage: node aes256cbc.mjs <-e|-d> <message|ciphertext> <key> <iv>");
  console.error("       node aes256cbc.mjs -k");
  console.error("       node aes256cbc.mjs -iv");
  process.exit(1);
}

const [mode, input, key, iv] = args;

if (Buffer.from(key, "hex").length > 32) {
  console.warn("Warning: Key is longer than 32 bytes. It will be truncated.");
  key = key.slice(0, 64);
} else if (Buffer.from(key, "hex").length !== 32) {
  console.error("Error: Key must be a 32-byte (64-character hexadecimal) string.");
  process.exit(1);
}

if (Buffer.from(iv, "hex").length !== 16) {
  console.error("Error: Initialization vector (IV) must be 16 bytes (32-character hexadecimal) long.");
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
