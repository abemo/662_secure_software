import math

def modinverse(a, m):
    # Extended Euclidean Algorithm to find the modular inverse
    m0, x0, x1 = m, 0, 1
    if m == 1:
        return 0
    while a > 1:
        # q is quotient
        q = a // m
        m, a = a % m, m
        x0, x1 = x1 - q * x0, x0
    if x1 < 0:
        x1 += m0
    return x1
  

p = 100392089237316158323570985008687907853269981005640569039457584007913129640081 
q = 90392089237316158323570985008687907853269981005640569039457584007913129640041
e = 65537
k = 512 
block_size = 60
print("block size: ", block_size)

n = p * q
print("n: ", n)
d = modinverse(e, (p-1)*(q-1))
print("d: ", d)

corpus = "Scaramouche, Scaramouche, will you do the Fandango? 💃🏽"
print("corpus: ", corpus)
corpus_utf8 = corpus.encode('utf-8')
hex_output = ''.join(f'{byte:02x}' for byte in corpus_utf8)
print("hex output: ", hex_output)
blocks = [hex_output[i:i + block_size * 2] for i in range(0, len(hex_output), block_size * 2)]
if len(blocks[-1]) < block_size * 2:
  padding_length = block_size * 2 - len(blocks[-1])
  padding = '02' * (padding_length // 2)
  blocks[-1] += padding
print("blocks: ", blocks)
  
encrypted_blocks = []
for block in blocks:
  block_int = int(block, 16)
  encrypted_block = pow(block_int, e, n)
  encrypted_blocks.append(encrypted_block)

print("encrypted blocks: ", encrypted_blocks)

decrypted_blocks = []
for block in encrypted_blocks:
  decrypted_block = pow(block, d, n)
  decrypted_blocks.append(decrypted_block)
print("decrypted blocks: ", decrypted_blocks)

decrypted_hex = ''.join(f'{block:0{block_size * 2}x}' for block in decrypted_blocks)
decrypted_bytes = bytes.fromhex(decrypted_hex)
print("decrypted hex: ", decrypted_hex)
decrypted_utf8 = decrypted_bytes.decode('utf-8')
print("decrypted utf8: ", decrypted_utf8)
