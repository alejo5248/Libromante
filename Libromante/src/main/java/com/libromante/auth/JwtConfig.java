package com.libromante.auth;

public class JwtConfig {

	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA = "\r\n" + 
			"-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEogIBAAKCAQEAviwZT4fcxrbGpNFFv7CjtroPXMGRq2u8QT5FX6X9QoTNX72t\r\n" + 
			"icHXveSlSKrEGijmex4xpCyoMgBYg6NYSk+6bpd8FkHDj1S5cDssZ9y/thTe86xC\r\n" + 
			"22hpAT0WgDUlu1TUAXM9ym819SGLh6d37hLAlYQLtWu7gAbNLiEhbOxMm9TdpbZm\r\n" + 
			"hY8LOmxhNY+FINaF7QrkQIWBOgVBS8KxlxJNRYVWgHZpS6tbnf/+K8oEWd6F7nX0\r\n" + 
			"yK0rDm64msYXihen7ySN0jjVu840w2Khzwq2VgSyiMeRLYVqfRmPAzjAb+j2n6gy\r\n" + 
			"Zeqof9GAds4IBvtgOfobSCM5AI8If+08mRm2ewIDAQABAoIBAAVdhyVIRwFQPNB/\r\n" + 
			"mwEqgaPxIefTCkIU2w3YLOri6v9KceTmnBPL5UNVRRiEwN2gE8ibdHkWTXSDr/ue\r\n" + 
			"yynlj/P40CZsmpDbqhOxh7IydzIy6otja8t26aeASrc5SKQ9Y3HbB35DnvkYhjBA\r\n" + 
			"mFCeNNLDE/V9YZ/eEUHg62mATTUEQvUSuDGhP0Bibw46C9q/TYWuzklDkOvPM0Fw\r\n" + 
			"c4Bo0pye/2uDQb00PkPHQaI7ToR8ahUBOLEVYZwYqJLqaprzX/bwGT5dA6VQIngJ\r\n" + 
			"cAlbzOr1UVfmAZXT5Maj8OABTK4nEjOGoN/29zPo2hrcl11Hwm3zoKJ62wJhseNt\r\n" + 
			"gJIzjtECgYEA7R4bCjXllHr9sX3868NkRxAnTdysMAqKpeSVP2m8qjJKZpctcHBU\r\n" + 
			"Dh3CkawiC+kziStm5lwAc9k5as4RdIH+IlMYNdDw6mIg20YjEejfT0dkerPeAVOT\r\n" + 
			"kV/GlWfALb+jbZiUwNMsCJL+fwbZE2P4/mTTxKUkTvHbedCCi+gpkIMCgYEAzVD2\r\n" + 
			"bQQBZehR08MM148hEx0V/RzXxL3uNNvvJnZklkamgnZp45wSAt8ljYV6uyJQFVk9\r\n" + 
			"EJj4NxKOylP5ypQUIkuZICT3VpsyKAmnHW0vGj++f2HPMFd0mlJAoLpSZ0pEGgtA\r\n" + 
			"zQI4t54s3Bg4Nmt48DPYB/r78GS4x/zlf5TQcKkCgYBddkhiNexQIkps86QFqfaU\r\n" + 
			"rCFR53FXXlBYI0lQvXTIARqiZWRbtWrsRX6zvI2O9FSD9L+SfSEQiMbA+oiWy8ZH\r\n" + 
			"8UjRrZHIUPVyeKrKHGgo75MDanJPQ4oe+wljSonNfoQEZuf7OUFZ/9agayqVIgq6\r\n" + 
			"+zMdh5pQI7TGxxEt5Ey90QKBgEdZpnmWFcJLbPxRYUob5FuXFQgkGNu3QWu/LqnI\r\n" + 
			"JQaQQNc/vWaq2xuCXrOCuncD4p64p174OfIxkcvASSNcOG7+pJUmI4pGXrTJPfkc\r\n" + 
			"82yE0HfGDtF24v+qK+lvNqnMhkQiuv9DkWBGKMLH6+kFij63VZDYaOtOpWRif4LJ\r\n" + 
			"vXIZAoGAFljwp54gvxqf5VdeNN7gnqMlL1ESfS31hf7y3qvcARxdR0CCSsbfQuFX\r\n" + 
			"R6EqayKCFuNBCQ1S5B1ZdxF06weXThxVEAuntLoOG9dMeIjTUpOkfQjnXhynQlBy\r\n" + 
			"fKSpp9ga1LEotj1G+CM2C0Xkh0yMba6DjwiEvnqo3+6WczMNcYA=\r\n" + 
			"-----END RSA PRIVATE KEY-----\r\n" ;
			 
			public static final String RSA_PUBLICA ="-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAviwZT4fcxrbGpNFFv7Cj\r\n" + 
			"troPXMGRq2u8QT5FX6X9QoTNX72ticHXveSlSKrEGijmex4xpCyoMgBYg6NYSk+6\r\n" + 
			"bpd8FkHDj1S5cDssZ9y/thTe86xC22hpAT0WgDUlu1TUAXM9ym819SGLh6d37hLA\r\n" + 
			"lYQLtWu7gAbNLiEhbOxMm9TdpbZmhY8LOmxhNY+FINaF7QrkQIWBOgVBS8KxlxJN\r\n" + 
			"RYVWgHZpS6tbnf/+K8oEWd6F7nX0yK0rDm64msYXihen7ySN0jjVu840w2Khzwq2\r\n" + 
			"VgSyiMeRLYVqfRmPAzjAb+j2n6gyZeqof9GAds4IBvtgOfobSCM5AI8If+08mRm2\r\n" + 
			"ewIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----\r\n" ;
}
