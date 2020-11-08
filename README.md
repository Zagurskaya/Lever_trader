"# Lever_trader" 

Registration:

1. GET http://localhost:8080/api/auth/signup
{  	"username": "Ivan2",
	"lastName": "Ivan2",
	"firstName": "Ivan2",
	"email": "natacha.r@mail.ru",
	"role": "TRADER"
    }
    
2. GET http://localhost:8080/api/auth/confirm?token=$2a$12$T0hT/hxBh3L4VYCiRyR68eow32QxhjKhuaBZp/Wa7qqxl.kStqsle

3. POST http://localhost:8080/api/auth/confirm?token=$2a$12$T0hT/hxBh3L4VYCiRyR68eow32QxhjKhuaBZp/Wa7qqxl.kStqsle
{  	"password": "123456789",
	"repeatPassword": "123456789"
    }
    
4. POST http://localhost:8080/api/auth/forgot_password
{  
    "email": "natacha.r@mail.ru"
    }
    
5. GET http://localhost:8080/api/auth/check_code
{  
    "code": "86388"
    }
    
6. POST http://localhost:8080/api/auth/reset
{  
    "code": "86388",
    "newPassword": "123456789"
    }
    
Guest:
1. GET/POST http://localhost:8080/api/traders

2. GET http://localhost:8080/api/traders/1

3. GET/POST http://localhost:8080/api/traders/1/comments

4. GET http://localhost:8080/api/traders/top

