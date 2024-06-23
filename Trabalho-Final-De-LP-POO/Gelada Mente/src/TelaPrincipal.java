import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TelaPrincipal extends JFrame {
    private JButton botaoVerCarrinho;
    private JButton botaoLogout;
    private Carrinho carrinho;
    private Estoque estoque;

    public TelaPrincipal(Carrinho carrinho, Estoque estoque) {
        this.carrinho = carrinho;
        this.estoque = estoque;

        setTitle("Estoque de Produtos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 590);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout());

        // Adicionando produtos ao estoque
        estoque.adicionarProduto(new Produto(1, "COCA COLA", 8.0, "C:\\WorkDir\\Trabalho-Final-De-LP-POO\\Gelada Mente\\src\\CocaCola.png"), 10);
        estoque.adicionarProduto(new Produto(2, "CERVEJA HEINEKEN", 11.0, "C:\\WorkDir\\Trabalho-Final-De-LP-POO\\Gelada Mente\\src\\heineken.png"), 10);
        estoque.adicionarProduto(new Produto(3, "WHISKY RED LABEL", 70.0, "C:\\WorkDir\\Trabalho-Final-De-LP-POO\\Gelada Mente\\src\\redlabel.png"), 10);
        estoque.adicionarProduto(new Produto(4, "ÁGUA", 4.0, "C:\\WorkDir\\Trabalho-Final-De-LP-POO\\Gelada Mente\\src\\agua.png"), 10);
        estoque.adicionarProduto(new Produto(5, "GUARANÁ ANTARTICA", 7.0, "C:\\WorkDir\\Trabalho-Final-De-LP-POO\\Gelada Mente\\src\\guarana.png"), 10);
        estoque.adicionarProduto(new Produto(6, "OUSADIA", 5.0, "C:\\WorkDir\\Trabalho-Final-De-LP-POO\\Gelada Mente\\src\\ousadia.png"), 10);

        JPanel painelProdutos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Adicionando itens de produto do estoque
        for (Produto produto : estoque.getProdutos().keySet()) {
            JPanel painelProduto = new JPanel();
            painelProduto.setLayout(new BoxLayout(painelProduto, BoxLayout.Y_AXIS));

            // Adicionando imagem do produto
            ImageIcon icon = new ImageIcon(produto.getCaminhoImagem());
            Image image = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            JLabel labelImagem = new JLabel(new ImageIcon(image));
            labelImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelProduto.add(labelImagem);

            // Adicionando nome e preço do produto
            JLabel labelNomePreco = new JLabel(produto.getNome() + " - R$" + produto.getPreco());
            labelNomePreco.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelProduto.add(labelNomePreco);

            // Adicionando JComboBox para selecionar o tamanho
            String[] tamanhos = {"300ml", "500ml", "1L", "2L"};
            JComboBox<String> comboBoxTamanhos = new JComboBox<>(tamanhos);
            comboBoxTamanhos.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelProduto.add(comboBoxTamanhos);

            // Botão de adicionar ao carrinho
            JButton botaoAdicionarCarrinho = new JButton("Adicionar ao carrinho");
            botaoAdicionarCarrinho.setAlignmentX(Component.CENTER_ALIGNMENT);
            botaoAdicionarCarrinho.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String tamanhoSelecionado = (String) comboBoxTamanhos.getSelectedItem();
                    carrinho.adicionarItem(produto, 1);
                    JOptionPane.showMessageDialog(null, produto.getNome() + " (Tamanho: " + tamanhoSelecionado + ") adicionado ao carrinho.");
                }
            });
            painelProduto.add(botaoAdicionarCarrinho);

            // Adicionando o painel do produto ao painel de produtos
            painelProdutos.add(painelProduto, gbc);
            gbc.gridx++;
            if (gbc.gridx > 2) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }

        // Botões na parte inferior da janela
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botaoVerCarrinho = new JButton("Ver Carrinho");
        botaoVerCarrinho.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TelaCarrinho(carrinho);
                dispose();
            }
        });

        botaoLogout = new JButton("Logout");
        botaoLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Logout realizado com sucesso.");
                // new TelaLogin(carrinho, estoque); // Você precisará substituir pela tela de login adequada
                dispose();
            }
        });

        painelBotoes.add(botaoVerCarrinho);
        painelBotoes.add(botaoLogout);

        // Adicionando os componentes ao painel principal
        painelPrincipal.add(painelProdutos, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        // Adicionando o painel principal à janela
        add(painelPrincipal);
        setVisible(true);
    }

    public static void main(String[] args) {
        Carrinho carrinho = new Carrinho();
        Estoque estoque = new Estoque();
        new TelaPrincipal(carrinho, estoque);
    }
}