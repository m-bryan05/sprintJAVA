/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Version;

public class BcryptHasher {

    private static final int COST_FACTOR = 6;
    private final BCrypt.Hasher hashAlg = BCrypt.with(Version.VERSION_2Y);
    
    public String hash(String rawPassword) {
        //generate a different salt for each user
        return hashAlg.hashToString(COST_FACTOR, rawPassword.toCharArray());
    }

    public boolean checkPassword(String passwordHash, String userInput) {
        if (passwordHash == null || passwordHash.isEmpty()) {
            return false;
        }

        return BCrypt.verifyer().verify(userInput.toCharArray(), passwordHash).verified;
    }
}
