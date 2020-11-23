# Vigenère Cipher

The Vigenère Cipher is a polyalphabetic substitution cipher.It was considered le chiffre ind hiffrable (French for the unbreakable cipher) 
for 300 years, until in 1863 Friedrich Kasiski published a successful attack on the Vigenère cipher. It was thought to be completely unbreakable 
for hundreds of years, and indeed, if very long keys are used the vigenere cipher can be unbreakable. But if short keys are used, or if we have 
a lot of ciphertext compared to the key length, the vigenere cipher is quite solvable.

The Vigenere Cipher uses the following tableau (the 'tabula recta') to encipher the plaintext:
 
 <p>
   <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Vigen%C3%A8re_square_shading.svg/1024px-Vigen%C3%A8re_square_shading.svg.png" width="350" height="400">
 </p>
 
 The 'key' for a vigenere cipher is a key word. e.g. 'CRYPTOGRAPHY'
 
 To encipher a message, repeat the keyword above the plaintext:
 
 ```CRYPTOGRAPHYCRYPTOGRAPHYCRYP```  &larr; ```key```
 
 ```DEFENDTHEEASTWALLOFTHECASTLE``` &larr; ```plainText```
 
 Now we take the letter we will be encoding, 'D', and find it on the first column on the tableau. Then, we move along the 'D' row of the 
 tableau until we come to the column with the 'C' at the top (The 'C' is the keyword letter for the first 'D'), the intersection is our ciphertext character, 'F'.
 
 So, the ciphertext for the above plaintext is:
 
 ```CRYPTOGRAPHYCRYPTOGRAPHYCRYP```  &larr; ```key```
 
 ```DEFENDTHEEASTWALLOFTHECASTLE``` &larr; ```plainText```
 
 ```FVDTGRZYETHQVNYAECLKHTJYUKJT``` &larr; ```cipherText```
 
 ---
 
The java program that I created as part of a project for the course SECURITY OF NETWORKS AND INTERNET APPLICATIONS, makes 
cryptanalysis of a cipherText for which the Vigenere algorithm was used and tries to discover the key with which the encryption 
was done by knowing the original plainText, the cipherText and the size of the key.