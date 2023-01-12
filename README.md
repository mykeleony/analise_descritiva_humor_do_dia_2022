# Análise descritiva do dataset "Humor do dia"

O *dataset* "***dias***" representa a atividade de psicologia positiva conhecida
como "**rastreamento de humor**", do inglês "*mood tracking*", que consiste em
registrar o próprio humor em intervalos de tempo definidos. Nesse caso, o humor
predominante foi registrado diariamente no ano de 2022 e podia assumir um dos
seguintes valores:

- Feliz
- Triste
- Calmo
- Estressante
- Entediante
- Ok (normal)
- Cheio
- Cansativo

<br>

Além do humor, o *dataset* conta com variáveis que representam as informações da
data das observações, como:

- Dia do mês
- Dia do ano
- Dia da semana
- Mês
- Ano



O presente projeto trata-se de uma análise descritiva do *dataset* "dias" (ou
humor do dia) e possui o propósito de elucidar a visão geral dos dados e
identificar tendências e padrões. Particularmente, o objetivo subjetivo desta
iniciativa é aumentar o autoconhecimento da criadora dos *inputs* dos dados,
minha linda namorada Débora.


---

### Atenção: a análise descritiva baseou-se, em grande parte, em ***outputs gráficos*** disponíveis [aqui](https://colab.research.google.com/drive/1YLn6YGEA09wARLftmXTyuQJ9DurZ6Jk8?authuser=1#scrollTo=lPviCk7msvWd). O  presente arquivo possui o propósito de esclarecer as etapas da análise e as conclusões obtidas a partir de cada uma.

<br>

## População dos dados

O *input* dos dados foi realizado manualmente por meio de um programa em Java
que agrupa cada entrada de humor no respectivo dia e gera um arquivo *CSV* com
as informações ao utilizar a biblioteca OpenCsv. O código desse programa está
disponível
[aqui](https://github.com/mykeleony/analise_descritiva_humor_do_dia_2022).

O *hard input* foi necessário porque as informações estavam representadas
graficamente (cada cor em uma célula da matriz de dia e mês representa um
humor), e infelizmente ainda não tenho conhecimento suficiente para criar uma
inteligência artificial que identifique o humor a partir da cor e da linha, pois
ainda estou estudando o assunto.

<br>

## Carregando os dados e realizando uma revisão geral

```python
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np
import colorcet as cc

dados = pd.read_csv("dias.csv")

print(dados.head())
print(dados.info())
```

É possível observar que não existem valores nulos no *dataset*, o que já era
esperado ao notar que existem 365 observações (total de dias de um ano não
bissexto, que é o caso de 2022).

Como as estatísticas básicas como médias e desvios padrão não são relevantes
pelo fato de as variáveis quantitativas representarem apenas informações de
data, partiremos direto para a Análise Exploratória de Dados (EDA) da variável
humor (as demais variáveis qualitativas também representam informações de data).

<br>

## Gráfico de barras da variável "Humor"

```python
sns.set_palette('Purples')

sns.countplot(data=dados, x='HUMOR')
plt.title('Distribuição do Humor de Débora em 2022')
plt.xlabel('Humor predominante', fontsize=12)
plt.ylabel('Ocorrências', fontsize=9)
plt.xticks(rotation=45)
```

Podemos notar que a esmagadora maioria dos dias foram felizes e normais.
Particular e felizmente, dias felizes representam quase um terço do ano. Por
outro lado, calmaria não foi o forte do 2022 de Débora, com a quantidade de dias
cheios, cansativos, estressantes e entediantes tomando conta dos dias não tão
felizes.

<br>

## Gráfico de pizza (setores) do humor

```python
colors = [cc.rainbow[i*20] for i in range(len(frequencias))]
plt.pie(frequencias.values, labels=frequencias.index, colors=colors)
plt.title('Distribuição do Humor')
```

Esta é outra maneira de evidenciar a predominância de felicidade e normalidade
no 2022 de Débora. Também é possível ter mais noção da dimensão do tédio e do
cansaço, os quais também foram dominantes.

<br>

## Frequência cruzada entre humor e dia da semana

```python
tabela = pd.crosstab(dados.HUMOR, dados.DIADASEMANA)
sns.heatmap(tabela, annot=True, cmap='Purples', fmt='g')
plt.title('Frequência Cruzada Humor x Dia da Semana')
```

Como a maioria dos brasileiros, Débora ficou feliz muito mais frequentemente em
dias não úteis (sábado e domingo), com esses dias representando 42% dos dias
felizes. Se incluirmos sexta-feira, a proporção sobe para 55%.

Por outro lado, em concordância com a minha convicção de domingo ser o dia mais
chato da semana, muitos domingos foram entediantes ou normais, sem nada de muito
interessante. Tédio e normalidade comporam 48% dos domingos de Débora.

Ademais, o dia útil que mais concentrou dias felizes foi terça-feira, e o motivo
exige um pouco de contexto: eu e Débora temos o *hobby* de colecionar pelúcias
capturadas nas [máquinas de diversão](https://www.diverbras.com.br/maquinas-diversao-gruas#:~:text=As%20m%C3%A1quinas%20divers%C3%A3o%20gruas%20s%C3%A3o%20alternativas%20acess%C3%ADveis%20e%20vi%C3%A1veis%20para,o%20ambiente%20familiar%20e%20descontra%C3%ADdo.),
e por meio da experiência sabemos que a captura é mais provável quando a máquina está cheia de pelúcias.
Coincidentemente, o lugar em que costumamos jogar abastece as máquinas às terças-feiras, e por isso
costumávamos nos encontrar nesses dias para nos divertir.

<br>

## Frequência cruzada entre humor e mês

```python
tabela = pd.crosstab(dados.HUMOR, dados.MES)
sns.heatmap(tabela, annot=True, cmap='Purples', fmt='g')
plt.title('Humor x Mês')
```

Começando pela felicidade, é possível perceber que a maior parte dos dias
felizes se concentrou no fim do ano, principalmente em agosto, outubro e
novembro. Esse período correspondeu à formatura de Débora no curso de 
Auxiliar de Médico Veterinário que durara mais de um ano, e que foi seguido de
novos cursos em outras vertentes da profissão, o que sugere uma realização
profissional e vocacional nesse período.

Segundamente, é possível notar que dias tristes se concentraram no começo do ano,
indicando uma possível acúmumlo de acontecimentos desagradáveis nesse período. 
Por outro lado, o tédio não poupou esforços e se espalhou pelo ano inteiro,
mas com dezembro sendo o mês menos tedioso do ano.

Outro ponto interessante está presente nos dias cansativos: quase um terço
deles (27,3%) está no mês de dezembro, que foi o mês em que Débora foi
contratada para uma função exaustiva em uma empresa. A outra concentração de
dias cansativos está em abril, o qual, de acordo com a própria, foi um mês de
estudos intensos.

Para fins de referência, eu a conheci em março. Não há dias tristes nem calmos
nesse mês. Tire suas próprias conclusões.

<br>

## Boxplot dos dias pelo humor

```python
sns.boxplot(x=dados.HUMOR, y=dados.DIADOANO, color='purple')
plt.title('Distribuição dos dias pelo humor')
plt.xlabel('Humor')
plt.ylabel('Diadoano')
plt.xticks(rotation=45)
```


Note que dias cansativos tendem muito a um lado, evidenciando novamente a sua
concentração em certos períodos do ano. Dias normais, tristes e entediantes são
os mais bem distribuídos pelo ano, podendo indicar uma saúde mental
aparentemente normal em 2022.

<br>

## Conclusões

Uma análise descritiva do dataset "dias" mostrou que ele contém informações
sobre o ano, dia do ano, dia do mês, dia da semana e humor de cada dia. Através
de estatísticas básicas, como frequências e proporções,
foi possível obter uma visão geral dos dados e identificar tendências e padrões.

Além disso, utilizando gráficos e visualizações, foi possível explorar mais
detalhadamente as relações entre as variáveis e identificar suas distribuições e
tendências. Por exemplo, foi possível verificar que felicidade é a sensação
diária mais comum e que há uma tendência de ocorrer em fins de semana, assim
como foi observado que a tristeza foi mais frequente no início do ano.

Porém, essa é apenas uma análise descritiva dos dados outras análises fazem-se
necessárias para aprofundamento, como análises multivariadas inferenciais para
obter uma compreensão em completude dos dados e verificar hipóteses mais
específicas.
