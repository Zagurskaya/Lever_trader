"# Lever_trader" 

1. GET http://localhost:8080/api/auth/signup
{  	"username": "Ivan2",
	"lastName": "Ivan2",
	"firstName": "Ivan2",
	"email": "ivan2@tut.by",
	"role": "TRADER"
    }
2. GET http://localhost:8080/api/auth/confirm?token=$2a$12$T0hT/hxBh3L4VYCiRyR68eow32QxhjKhuaBZp/Wa7qqxl.kStqsle

3. POST http://localhost:8080/api/auth/confirm?token=$2a$12$T0hT/hxBh3L4VYCiRyR68eow32QxhjKhuaBZp/Wa7qqxl.kStqsle
{  	"password": "123456789",
	"repeatPassword": "123456789"
    }
4. http://localhost:8080/api/auth/forgot_password
{  
    "email": "natacha.r@mail.ru"
    }