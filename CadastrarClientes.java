import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/**
 * Classe principal que cria o formulário de Cadastro de Clientes em Java Swing.
 * (Versão Original - Padrão Swing)
 */
public class CadastrarClientes extends JFrame {

    // Declaração dos campos como variáveis de instância para acesso na validação
    private JTextField nomeCompletoField;
    private JTextField enderecoField;
    private JTextField cidadeField;
    private JComboBox<String> estadoComboBox;
    private JFormattedTextField telefoneField;
    private JRadioButton ativoRadio;
    private JRadioButton inativoRadio;

    public CadastrarClientes() {
        // --- Configuração da Janela Principal ---
        setTitle("Cadastrar Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a aplicação ao fechar a janela
        setLayout(new BorderLayout()); // Layout principal da janela

        // --- 1. ToolBar (Painel Superior) ---
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false); // Impede que a barra seja movida

        // Botão Gravar
        JButton gravarButton = new JButton("Gravar Dados");
        gravarButton.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
        gravarButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        gravarButton.setHorizontalTextPosition(SwingConstants.CENTER);
        gravarButton.addActionListener(new GravarDadosListener()); // Adiciona o 'listener' da validação

        // Botão Cancelar
        JButton cancelarButton = new JButton("Cancelar Cadastro");
        cancelarButton.setIcon(UIManager.getIcon("InternalFrame.closeIcon")); // Ícone 'X' padrão
        cancelarButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        cancelarButton.setHorizontalTextPosition(SwingConstants.CENTER);
        // Adiciona um listener simples para fechar a janela
        cancelarButton.addActionListener(e -> dispose()); 

        toolBar.add(gravarButton);
        toolBar.add(cancelarButton);
        add(toolBar, BorderLayout.NORTH); // Adiciona a barra de ferramentas ao topo

        // --- 2. Painel do Formulário (Painel Central) ---
        JPanel formPanel = new JPanel(new GridBagLayout());
        // Adiciona uma borda interna (padding)
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Faz componentes de texto esticarem

        // --- Linha 0: Título ---
        JLabel tituloLabel = new JLabel("Preencha os dados corretamente e clique em Gravar Dados");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // Ocupa 3 colunas
        gbc.anchor = GridBagConstraints.WEST; // Alinha à esquerda
        formPanel.add(tituloLabel, gbc);

        // Reseta o gridwidth
        gbc.gridwidth = 1;

        // --- Linha 1: Nome Completo ---
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST; // Alinha o label à direita
        formPanel.add(new JLabel("Nome Completo:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2; // Campo ocupa 2 colunas
        gbc.anchor = GridBagConstraints.WEST;
        nomeCompletoField = new JTextField(30); // 30 colunas de largura preferida
        formPanel.add(nomeCompletoField, gbc);

        // --- Linha 2: Endereço ---
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Endereço:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        enderecoField = new JTextField(30);
        formPanel.add(enderecoField, gbc);

        // --- Linha 3: Cidade ---
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Cidade:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        cidadeField = new JTextField(30);
        formPanel.add(cidadeField, gbc);

        // --- Linha 4: Estado ---
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Estado:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 1; // ComboBox ocupa 1 coluna
        gbc.anchor = GridBagConstraints.WEST;
        String[] estados = {
            "Selecione...", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO",
            "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ",
            "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
        };
        estadoComboBox = new JComboBox<>(estados);
        formPanel.add(estadoComboBox, gbc);

        // --- Linha 5: Telefone ---
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Telefone:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        try {
            // Define a máscara (##) ####-#### como na imagem
            MaskFormatter mask = new MaskFormatter("(##) ####-####");
            mask.setPlaceholderCharacter('_');
            telefoneField = new JFormattedTextField(mask);
            telefoneField.setColumns(12); // Tamanho ajustado para a máscara
        } catch (ParseException e) {
            e.printStackTrace();
            telefoneField = new JFormattedTextField(); // Fallback caso a máscara falhe
        }
        formPanel.add(telefoneField, gbc);

        // --- Linha 6: Status ---
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Status:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        ativoRadio = new JRadioButton("Ativo", true); // "Ativo" pré-selecionado
        inativoRadio = new JRadioButton("Inativo");
        
        // Agrupa os radio buttons para que apenas um possa ser selecionado
        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(ativoRadio);
        statusGroup.add(inativoRadio);

        // Painel para organizar os radio buttons horizontalmente
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        statusPanel.add(ativoRadio);
        statusPanel.add(inativoRadio);
        formPanel.add(statusPanel, gbc);
        
        // --- Adiciona o painel do formulário ao centro da janela ---
        add(formPanel, BorderLayout.CENTER);

        // --- Finaliza a configuração da janela ---
        pack(); // Ajusta o tamanho da janela automaticamente ao conteúdo
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setResizable(false); // Impede o redimensionamento
    }

    /**
     * Classe interna que implementa a lógica de validação do botão "Gravar Dados".
     */
    private class GravarDadosListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder erros = new StringBuilder();

            // 1. Validar Nome Completo
            if (nomeCompletoField.getText().trim().isEmpty()) {
                erros.append("- Nome Completo\n");
            }

            // 2. Validar Endereço
            if (enderecoField.getText().trim().isEmpty()) {
                erros.append("- Endereço\n");
            }

            // 3. Validar Cidade
            if (cidadeField.getText().trim().isEmpty()) {
                erros.append("- Cidade\n");
            }

            // 4. Validar Estado
            if (estadoComboBox.getSelectedIndex() == 0) { // "Selecione..." é o índice 0
                erros.append("- Estado\n");
            }

            // 5. Validar Telefone
            // getValue() retorna null se o texto não for válido para a máscara (ex: incompleto)
            if (telefoneField.getValue() == null) {
                erros.append("- Telefone (incompleto ou inválido)\n");
            }
            
            // 6. Apresentar resultado da validação
            if (erros.length() > 0) {
                // Se 'erros' não está vazio, mostra a mensagem de erro
                JOptionPane.showMessageDialog(
                        CadastrarClientes.this, // Janela "pai"
                        "Os seguintes campos precisam ser preenchidos:\n\n" + erros.toString(),
                        "Erro de Validação", // Título da janela de diálogo
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                // Sucesso! Todos os campos estão preenchidos.
                JOptionPane.showMessageDialog(
                        CadastrarClientes.this,
                        "Dados gravados com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    /**
     * Método principal (main) para executar a aplicação.
     */
    public static void main(String[] args) {
        // Tenta definir o Look and Feel para se parecer com o sistema operacional nativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Garante que a GUI seja criada na Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            CadastrarClientes frame = new CadastrarClientes();
            frame.setVisible(true);
        });
    }
}