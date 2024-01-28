# API Sistema de Saúde


## Descrição
	Este projeto foi escrito em Java com Spring Boot, além de utilizados H2 para banco de dados e JWT para autenticação.


## Instalação e Uso
	Baixe / clone o repositório:
        git clone https://github.com/kaesare/saude.git

	Crie o pacote com Maven:
        mvn clean package

	Inicie a aplicação:
        java -jar target/saude-0.0.1-SNAPSHOT.jar

	Para acessar os endpoints utilize:
        http:localhost:8080


## API Endpoints e Swagger

	Seguem os endpoints disponíveis:

		POST    /registra
		POST    /login
		GET     /beneficiarios
		POST    /beneficiarios
		DELETE  /beneficiarios/{id} (Necessário ADMIN)
		PATCH   /beneficiarios/{id}
		GET     /beneficiarios/{id}/documentos
		PATCH   /documentos/{id}

	Também é possível acessar os endpoints pelo Swagger:
        http://localhost:8080/swagger-ui/index.html


## Collection (Postman)
	
    Uma collection foi gerada via Postman com todos os endpoints e exemplos preenchidos. 
    Pode ser importada a partir do arquivo:
        saude.postman_collection.json
