
1. Antes de tudo, você precisa ter um schema no banco de dados chamado docktech. O FLYWAY vai 
se encarregar de criar as tabelas iniciais e popular a tabela de pessoas com 3 usuarios ficticios 
da Disney :)


Para testar os endpoints criados, você pode usar os seguintes comandos curl.

 ```
curl \
 -H 'Content-type: application/json' \
 -d '
        {
        "idPessoa": "1", 
        "saldo": "30.8", 
        "limiteSaqueDiario": "1", 
        "flagAtivo": "true",
        "tipoConta": "1",
        "dataCriacao": "2020-01-01T01:01:01"
        }
]' \
 -X POST 'http://localhost:8030/api/conta'


 curl -X PUT 'http://localhost:8030/api/conta/deposito/1/50.8/2020-05-01'
 curl -X PUT 'http://localhost:8030/api/conta/saque/1/20.8/2021-03-01'
 curl -X GET 'http://localhost:8030/api/conta/saldo/1'
 curl -X PUT 'http://localhost:8030/api/conta/bloqueio/1'
 curl -X PUT 'http://localhost:8030/api/conta/ativacao/1'
 curl -X GET 'http://localhost:8030/api/transacao/by-conta/1'
 curl -X GET 'http://localhost:8030/api/transacao/by-conta/periodo/1/2020-01-01/2020-12-12'
 ```

Além de tudo isso, você tem acesso à testes unitários (integração na verdade) verificando a funcionalidade do código dentro dos services!