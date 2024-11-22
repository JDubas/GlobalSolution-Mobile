# Hall9000 - Aplicativo de Monitoramento de Consumo de Energia

Hall9000 é um aplicativo móvel desenvolvido para ajudar os usuários a monitorarem o consumo de energia de seus eletrodomésticos e calcular o custo de energia de cada aparelho. O app permite cadastrar eletrodomésticos, inserir suas especificações (como potência, frequência de uso, e tempo de uso diário, semanal ou mensal), e exibe o consumo em kWh e o custo associado à energia consumida.

## Link para o video:
https://youtu.be/cGakxtmTSxI

## Funcionalidades

- **Cadastro de Aparelhos**: O usuário pode adicionar eletrodomésticos ao aplicativo e informar a potência, frequência de uso, e tempo de uso diário, semanal ou mensal.
- **Cálculo de Consumo**: O aplicativo calcula o consumo de energia de cada aparelho em kWh com base nos dados fornecidos.
- **Cálculo de Custo de Energia**: O custo de energia é calculado com base no consumo e na tarifa de energia local, permitindo ao usuário ver quanto ele está pagando por cada aparelho.
- **Histórico de Consumo**: O app armazena os dados de consumo para que o usuário possa acompanhar e comparar os gastos ao longo do tempo.

## Pré-requisitos

Antes de começar, certifique-se de ter o seguinte instalado em sua máquina:

- **Android Studio**: Para desenvolvimento e execução do app.
- **Java Development Kit (JDK)**: Para compilar o código do Android.
- **Emulador ou dispositivo Android**: Para rodar o aplicativo.
- **Internet**: Para acessar a API que fornece os dados.

## Como Usar

### Cadastro de Aparelhos

1. Abra o aplicativo Hall9000.
2. Vá para a seção de "Cadastro de Aparelho".
3. Insira os seguintes dados:
   - **Nome do Aparelho**: O nome do seu aparelho (ex: "Geladeira", "Ar Condicionado").
   - **Potência (em W)**: A potência do aparelho (ex: 1000W para um micro-ondas).
   - **Frequência de Uso**: A frequência com que o aparelho é usado (Diário, Semanal ou Mensal).
   - **Hora de Uso**: O tempo médio que o aparelho fica ligado por dia, semana ou mês.

### Cálculo de Consumo e Custo

Após cadastrar o aparelho, o aplicativo irá calcular automaticamente o consumo de energia em kWh e o custo baseado na tarifa de energia local.

#### Fórmula de Cálculo:

- **Consumo em kWh**:
  \[
  Consumo (kWh) = \frac{Potência (W) \times Tempo de Uso (horas)}{1000}
  \]

- **Custo**:
  \[
  Custo = Consumo (kWh) \times Tarifa de Energia (R$/kWh)
  \]

### Exemplo de Cálculo

Se o usuário tiver uma geladeira de 200W que fica ligada 24 horas por dia, o cálculo seria:

- **Consumo diário**:
  \[
  Consumo = \frac{200W \times 24h}{1000} = 4.8 kWh/dia
  \]
- **Custo diário** (com uma tarifa de energia de R$0,80 por kWh):
  \[
  Custo = 4.8 \, kWh \times 0.80 = R$3.84/dia
  \]

### Recuperação de Senha

Se o usuário esquecer a senha, o aplicativo possui uma funcionalidade para recuperar a senha por e-mail. Basta inserir o e-mail cadastrado, e a senha será enviada de volta ao e-mail.

### Visualização do Consumo de Energia

O aplicativo exibe de forma clara o total consumido por cada aparelho e o custo associado. Isso ajuda o usuário a identificar quais aparelhos consomem mais energia e quais podem ser ajustados para economizar na conta de luz.
