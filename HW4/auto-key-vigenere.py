def auto_key_vigenere(plaintext: str, key: str) -> str:
    cipher = ""
    
    for i in range(len(plaintext)):
      if i >= len(key):
        key += plaintext[i]
      p = ord(plaintext[i]) - ord('A')
      k = ord(key[i]) - ord('A')
      c = (p + k) % 26
      cipher += chr(c + ord('A'))

    return cipher
  
print(auto_key_vigenere(plaintext="TAKEACOPYOFYOURPOLICYTONORMAWILCOXONTHETHIRDFLOOR",
                  key="QUARK"))