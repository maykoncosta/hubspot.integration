# HubStop Integration Service

## ✨ Descrição do Problema e Solução
Este projeto é uma solução backend desenvolvida para integrar um sistema externo com a API do HubSpot. A aplicação permite:

- Autenticação OAuth com o HubSpot.
- Criação de contatos na plataforma HubSpot.
- Armazenamento e renovação segura do token de acesso.
- Recebimento e tratamento de eventos via webhook do tipo `contact.creation`.
- Armazenamento local dos eventos recebidos via webhook.

Tudo isso implementado com foco em **segurança** e **boas práticas de engenharia**.

## 💡 Escopo
Esta é uma aplicação **100% backend**, desenvolvida com Spring Boot.
# HubStop Integration

## 📌 Descrição do Problema
Integração com a API do HubSpot para permitir:
- Autenticar usuários via OAuth2 com a API do HubSpot
- Armazenar tokens no H2
- Criação de contatos via endpoint interno
- Recebimento de eventos de webhook do tipo `contact.creation`
- Armazenamento seguro de tokens e resiliência a rate limit

## 🎯 Solução
Uma aplicação backend REST desenvolvida com Spring Boot, responsável por:
- Realizar autenticação OAuth com a API do HubSpot
- Gerenciar e renovar tokens com segurança
- Criar contatos e reagir a eventos de webhook recebidos
- Persistir dados temporários em banco H2

## 🛠️ Foco
100% back-end (Java + Spring)

## 🧠 Decisões Técnicas e Justificativas

| Ferramenta | Justificativa prática |
|-----------|------------------------|
| **Java 21** | Última LTS com recursos modernos e melhorias de desempenho. Ideal para novos projetos sem risco de obsolescência. |
| **Spring Boot 3.4.5** | Facilita a criação de APIs REST com configurações mínimas e integração robusta com o ecossistema Spring. |
| **Maven** | Simples, estável e amplamente usado no mercado. Resolve bem o problema de dependências e empacotamento. |
| **Flyway** | Adotado para versionamento automático de schema do banco de dados. Elimina a necessidade de rodar scripts manualmente e garante que ambientes diferentes estejam sempre consistentes.
| **H2 Database** | Banco em memória ideal para testes rápidos e desenvolvimento local. Não exige instalação nem configuração extra. |
| **Lombok** | Reduz código repetitivo (getters, setters, construtores), tornando as classes mais legíveis e enxutas, sem comprometer o desempenho ou a clareza. |
| **dotenv-java** | Utilizado para carregar variáveis de ambiente durante o desenvolvimento local, mantendo o código limpo e desacoplado de configurações sensíveis. |
| **LocalTunnel** | Expoem o servidor local de forma pública e segura via HTTPS. Uma escolha simples e leve, sem necessidade de cadastro, e permitir a geração rápida de URLs públicas diretamente via terminal.
| **Arquitetura em Camadas** | Uma estrutura organizada e modularizada, que separa bem as responsabilidades, também é Bastante popular e aceita pela comunidade, o que facilita a familiaridade de outros desenvolvedores com o código. |


## ⚖️ Trade-offs e Melhorias Futuras
- A URL de webhook precisa ser exposta publicamente. Usamos LocalTunnel durante o desenvolvimento.
- Melhorias futuras:
  - Implementar filas (ex: Kafka) para garantir processamento assíncrono dos webhooks.
  - Testes de integração e de unidades.
  - Retry com backoff exponencial (ex: Resilience4j) para contornar rate limits.

## 💼 Instruções para Executar a Aplicação

### 1. Clonar e Rodar
```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
./mvnw spring-boot:run
```

A aplicação rodará em:
```
http://localhost:8080
```

### 2. Configure o `.env`
Crie um arquivo `.env` na raiz com as seguintes variáveis:
```
HUBSPOT_CLIENT_ID=seu_client_id
HUBSPOT_CLIENT_SECRET=seu_client_secret
HUBSPOT_REFRESH_TOKEN=seu_refresh_token
SECURITY_CRYPTO_SECRET=1234567890123456
```

### 3. Banco de Dados
Banco H2 rodando em memória.
Acesse via:
```
http://localhost:8080/h2-console
```
JDBC URL:
```
jdbc:h2:mem:testdb
```

---

## 🌐 Webhook com LocalTunnel

### Instalar LocalTunnel
```bash
npm install -g localtunnel
```

### Rodar o Túnel
```bash
lt --port 8080
```

Você receberá uma URL como:
```
https://green-rat-40.loca.lt
```
Essa será sua URL pública para cadastrar no HubSpot.

> ⚠️ Ela muda a cada execução. Atualize no HubSpot sempre que for reiniciar o localTunnel.

---

## 🔧 Configurar Webhook no HubSpot

1. Acesse seu Private App em: `https://app.hubspot.com/private-apps`
2. Clique em **Webhooks** → **Create Subscription**
3. Configure:
   - **Event type:** `contact.creation`
   - **Webhook URL:** `https://sua-url.loca.lt/contacts/webhook`
   - Método: `POST`
4. Salve e ative.

Quando um contato for criado no CRM, a aplicação será notificada e o evento salvo no banco.

---

Configure essa URL no painel de Webhooks do HubSpot.

## 🔗 Endpoints Disponíveis
| Método | Rota                | Descrição                          |
|--------|---------------------|-------------------------------------|
| POST   | /contacts           | Cria contato na API do HubSpot      |
| POST   | /contacts/webhook  | Recebe eventos de contact.creation  |

## 🎓 Links
- ✨ [Meu perfil GitHub](https://github.com/maykoncosta)
- 🔗 [Meu perfil Linkedin](https://linkedin.com/in/maykon-costa)



