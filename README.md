# API de Estufa Inteligente para Agricultura Espacial

## 1º Global Solution 2026

### Integrantes
- Eduado Mazelli - RM:553236
- Lucas Masaki - RM:553084
- Pedro Henrique Lima - RM:552746
- Carolina Cavalli - RM:552925
- Joseh Gabriel Trimboli Agra - RM: 553094

## Motivação
À medida que a exploração espacial avança, viver em outros planetas deixa de ser apenas imaginação e passa a ser uma possibilidade para o futuro. 
Pensando nos desafios dessa nova realidade, surgiu a ideia de criar uma Estufa Inteligente para Agricultura Espacial, começando por Marte, um dos planetas mais estudados para uma possível colonização.

## Funcionalidades do sistema
- Cadastro e gerenciamento de estufas e sensores
- Registro de leituras com cálculo automático de nível de alerta
- Histórico de leituras por sensor com filtro de período
- Relatório consolidado por estufa com médias por tipo de sensor e contagem de alertas
- Autenticação JWT stateless com três perfis de acesso
- Validação de leituras com alertas descritivos

## Execução
### 1- Criar banco de dados
Utilize o comando: <br>
CREATE DATABASE space_db

### 2- Configurar credenciais
Modifique o que está em aspas (Ex.: "nome" -> nome) <br>
spring.datasource.url=jdbc:mysql://localhost/space_db <br>
spring.datasource.username="root" <br>
spring.datasource.password="sua_senha" <br>
api.security.token.secret=${JWT_SECRET:"sua_chave"} <br>
