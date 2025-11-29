# Sistema Bancário: Exceções em Java

> Implementação educacional de um sistema bancário robusto com tratamento profissional de exceções.

## 📋 Descrição

Projeto desenvolvido para consolidar conhecimentos sobre:
- Hierarquia de exceções (Exception vs RuntimeException)
- Diferença entre Checked e Unchecked exceptions
- Try-catch-finally e Try-with-resources
- Criação de exceções customizadas
- Boas práticas de tratamento de erros

## 🎯 Objetivo

Aprender **além da sintaxe**. Entender **por que** usamos exceções e **quando** usá-las em código de produção.

## ✨ Funcionalidades

- ✅ Criação de contas bancárias
- ✅ Depósitos e saques com validação
- ✅ Transferências entre contas
- ✅ Inativação de contas
- ✅ 5 exceções customizadas (checked e unchecked)
- ✅ 13 cenários de testes abrangentes

## 🏗️ Arquitetura

### Exceções Implementadas

| Exceção | Tipo | Caso de Uso |
|---------|------|-----------|
| `ContaNaoEncontradaException` | Unchecked | Conta não existe |
| `SaldoInsuficienteException` | Checked | Saldo menor que operação |
| `ContaInativaException` | Checked | Operação em conta inativa |
| `ValorInvalidoException` | Unchecked | Valor negativo/zero |
| `TransferenciaException` | Checked | Falha em transferência |

### Classes Principais

```
model.Conta             → Entidade de conta bancária
service.BancoService    → Lógica de negócio
exception.*             → Exceções customizadas
main.Main               → Testes unitários
```

## 🚀 Como Usar

### Pré-requisitos
- Java 17+
- Maven 3.8+ (ou Gradle)

### Setup

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/java-banco-sistema.git
cd java-banco-sistema

```

### Exemplo de Uso

```java
BancoService banco = new BancoService();

// Criar conta
Conta conta = banco.criarConta("001", "João Silva", 1000.00);

// Depositar
banco.depositar(conta.getNumero(), 500.00);

// Sacar com tratamento de exceção
try {
    banco.sacar(conta.getNumero(), 1600.00);
} catch (SaldoInsuficienteException e) {
    System.out.println("Saldo insuficiente: " + e.getMessage());
}
```

## 📊 Testes

Executar todos os 13 testes:

```
Rode a classe main
```

Output esperado: **13/13 PASSOU**

## 📚 Conceitos Aprendidos

- [x] Stack vs Heap (Módulo 1.1)
- [x] String Pool e `==` vs `.equals()` (Módulo 1.1)
- [x] Hierarquia de exceções
- [x] Checked vs Unchecked exceptions
- [x] Try-catch-finally
- [x] Try-with-resources
- [x] Exceções customizadas
- [x] Boas práticas de tratamento

## 🔗 Estrutura de Pastas

```
src/
├── main/java/com/seu_usuario/
│   ├── exception/
│   │   ├── ContaNaoEncontradaException.java
│   │   ├── SaldoInsuficienteException.java
│   │   ├── ContaInativaException.java
│   │   ├── ValorInvalidoException.java
│   │   └── TransferenciaException.java
│   ├── model/
│   │   └── Conta.java
│   ├── service/
│   │   └── BancoService.java
│   └── main/
│       └── Main.java
```

## 📝 Detalhes de Implementação

### BancoService

7 métodos principais:
- `criarConta()` — Validação de saldo inicial
- `buscarConta()` — Lookup com exceção customizada
- `depositar()` — Crédito com validação
- `sacar()` — Débito com múltiplas validações
- `transferir()` — Operação complexa com encapsulamento de erros
- `inativarConta()` — Mudança de estado
- `consultarSaldo()` — Query simples

### Fluxo de Validação

```
Entrada → Validação → Busca → Verificação Estado → Operação → Retorno
```

## 🧪 Cobertura de Testes

- ✅ Cenários de sucesso (3 testes)
- ✅ Validação de entrada (3 testes)
- ✅ Exceções esperadas (7 testes)

Total: 13 testes, 100% de cobertura das funcionalidades

## 🎓 Aprendizados-Chave

### Diferença Entre Exceções

**Checked (IOException, SQLException)**
- Compilador força tratamento
- Problemas recuperáveis e externos
- Use `try-catch` ou `throws`

**Unchecked (NullPointerException, IllegalArgumentException)**
- Compilador não força
- Erros de lógica/programação
- Prevenha com validações

### Quando Usar Cada Uma?

```java
// CHECKED: Problema externo e recuperável
public void lerArquivo() throws IOException { }

// UNCHECKED: Erro de programação
if (valor < 0) throw new IllegalArgumentException();

// CHECKED + CAUSE: Encapsule com contexto
try {
    operacao();
} catch (IOException e) {
    throw new TransferenciaException("msg", e); // Preserva causa
}
```

## 🔄 Evolução Futura

- [ ] Persistência em banco de dados
- [ ] API REST com Spring Boot
- [ ] Testes com JUnit 5
- [ ] CI/CD com GitHub Actions
- [ ] Docker containerization

## 💡 Dicas de Estudo

1. Leia a teoria em `docs/` primeiro
2. Implemente as exceções
3. Rode os testes
4. Modifique cenários para entender limites
5. Estude o stack trace de erros

## 📖 Referências

- [Oracle Java Exceptions Documentation](https://docs.oracle.com/javase/tutorial/essential/exceptions/)
- [Effective Java - Chapter 6: Exceptions](https://www.oreilly.com/library/view/effective-java-3rd/9780134685991/)

## 👨‍💻 Autor

**Matheus Junio Ribeiro da Silva**  
Desenvolvedor em formação | Backend Java | OI Telecom

## 📄 Licença

MIT License - Veja [LICENSE](LICENSE) para detalhes.

---

**⭐ Se este projeto te ajudou, deixe uma estrela!**
