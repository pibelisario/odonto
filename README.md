# Proteto Odonto
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/pibelisario/odonto/blob/master/LICENSE) 

# Sobre o projeto

https://wmazoni-sds1.netlify.app

O projeto Odonto veio de uma necessidade de simplificar o método em que eram contabilizados os atendimentos dos dentistas na empresa em que trabalho.

Até a criação do projeto a contabilização de atendimentos de cada dentista era mensalmente feita por meio de uma planilha Excel, onde muitos tinham dificuldade em lidar e também os erros e desconfiguração da planilha eram frequentes. Foi onde eu vi uma oportunidade de aplicar meus estudos em Spring Boot. Então decidi transformar essa planilha em uma aplicação web o mais simples possível de ser utilizada.

De forma resumida essa aplicação tem a finalidade de registar e contabilizar cada atendimento feito por cada dentista. Cada dentista tem seu usuário e senha para que ao final do mês seja gerado uma planilha dentro do próprio sistema com a quantidade de atendimento, emergências, tratamento, número de faltas, entre outras informações necessárias.

## Layout web

### Tela de Login

Cada dentista tem um login e senha para controle de acesso.

![web 0](https://github.com/pibelisario/odonto/blob/master/assets/tela%20de%20login.png?raw=true) 

### Tela de Cadastro de Atendimento

Essa tela é preenchida pelo dentista ou assistente, aqui contem as informações pessoais de cada paciente, necessárias para gerar o relatório de atendimento ao final de cada mês.

![web 1](https://github.com/pibelisario/odonto/blob/master/assets/tela%20cadastro%20de%20atendimentos.png?raw=true) 

### Tela lista de atendimentos

Aqui o dentista pode visualizar, editar ou excluir algum atendimento caso necessário. 
Também possui um campo busca por mês onde ele consegue visualizar atendimentos passados realizados.
Devido ao controle de acesso, os atendimentos que iram aparecer serão somente os que foram realizados pelo usuário que esta logado.

![web 2](https://github.com/pibelisario/odonto/blob/master/assets/tela%20de%20atendimentos.png?raw=true)

### Tela para gerar relatório mensal

Essa tela permite que o dentista selecione o mês desejado para gerar um relatório com todos os atendimentos.

![web 3](https://github.com/pibelisario/odonto/blob/master/assets/tela%20gerar%20relat%C3%B3rio.png?raw=true)

### Primeira página relatório

Aqui o dentista tem informações sobre o atendimento de cada paciente, contem informações como data, observações, dados pessoais entre outras informações.

![web 4](https://github.com/pibelisario/odonto/blob/master/assets/tela%20p%C3%A1g%201%20relat%C3%B3rio.png?raw=true)

### Página final

Está sempre será a última página de cada relatório. Essa página possui uma tabela com informações sobre a quantidade de atendimento e procedimentos realizados, com um campo para assinatura e carimbo de cada dentista para posteriormente ser impresso e entregue a diretoria.

![web 5](https://github.com/pibelisario/odonto/blob/master/assets/tela%20p%C3%A1g%202%20relat%C3%B3rio.png?raw=true)


# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven
- Spring Security
- LibrePDF
  
## Front end
- thymeleaf
- Bootstrap
- HTML
- CSS
- JS
  
## Implantação em produção
- Back end: AWS
- Banco de dados: MySQL

<!--
# Como executar o projeto

## Back end
Pré-requisitos: Java 11

```bash
# clonar repositório
git clone https://github.com/devsuperior/sds1-wmazoni

# entrar na pasta do projeto back end
cd backend

# executar o projeto
./mvnw spring-boot:run
```

## Front end web
Pré-requisitos: npm / yarn

```bash
# clonar repositório
git clone https://github.com/devsuperior/sds1-wmazoni

# entrar na pasta do projeto front end web
cd front-web

# instalar dependências
yarn install

# executar o projeto
yarn start
```
-->

# Autor

Paulo Inácio Belisario de Noronha

https://www.linkedin.com/in/paulo-in%C3%A1cio-belisario-de-noronha-392946b6/

