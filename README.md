# Projeto: Formulário de Cadastro de Clientes (Java Swing)

Este projeto é uma aplicação de desktop desenvolvida em Java Swing, que implementa um formulário para cadastro de clientes, conforme especificado na atividade acadêmica.

## Funcionalidades

* Interface gráfica de usuário (GUI) baseada na imagem de referência.
* Campos de formulário: Nome Completo, Endereço, Cidade, Estado (Dropdown), Telefone (com máscara `(##) ####-####`) e Status (Radio button).
* **Validação de Campos:** Ao clicar em "Gravar Dados", o programa executa uma verificação para garantir que todos os campos foram preenchidos corretamente.
* **Mensagem de Erro:** Caso algum campo obrigatório não esteja preenchido, uma janela de diálogo (`JOptionPane`) é exibida, informando ao usuário quais campos precisam ser preenchidos.
* **Mensagem de Sucesso:** Se todos os campos estiverem válidos, uma mensagem de confirmação de sucesso é apresentada.
* O botão "Cancelar Cadastro" fecha a janela da aplicação.

## Instruções de Execução (via Terminal)

Este projeto consiste em um único arquivo Java (`CadastrarClientes.java`) e não requer bibliotecas ou dependências externas. Siga os passos abaixo para compilar e executar o programa usando um terminal (como o terminal integrado do VSCode).

1.  **Navegue até o diretório:**
    Abra seu terminal e use o comando `cd` para navegar até a pasta onde você salvou o arquivo `CadastrarClientes.java`.

    ```bash
    # Exemplo:
    cd caminho/para/seu/projeto
    ```

2.  **Compile o arquivo Java:**
    Execute o seguinte comando para compilar o código-fonte. Este comando criará o arquivo `CadastrarClientes.class`.

    ```bash
    javac CadastrarClientes.java
    ```

3.  **Execute o programa:**
    Após a compilação ser concluída com sucesso (sem erros), execute o programa com o seguinte comando para iniciar a aplicação:

    ```bash
    java CadastrarClientes
    ```

A janela do "Cadastrar Clientes" deverá abrir na sua tela.