package com.brazucadev.userscrud.utils;

import org.mindrot.jbcrypt.BCrypt;

public class Bcrypt {
	// Gera um hash bcrypt da senha
	public static String hashPassword(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}

	// Verifica se a senha está correta
	public static boolean checkPassword(String plainPassword, String hashedPassword) {
		return BCrypt.checkpw(plainPassword, hashedPassword);
	}
}
