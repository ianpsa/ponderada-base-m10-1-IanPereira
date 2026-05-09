# Atividade Ponderada Dados (De jogar jogo de tabuleiro)

## Introdução

&emsp; Nesta atividade ponderada, considerei alguns pontos antes de iniciar o desenvolvimento: 

1. É valida a utilização de IA desde que seja para compreensão da sintaxe como: O uso de métodos, definição de variáveis funções, libs e etc.
2. Primeiro a Docs oficial e depois IA: busquei pesquisar o que eu gostaria de saber antes no google e me deparei com algunas documentações (ao fim do texto) o resto foi brute force mesmo hehe.
3. Ler os erros por mais que muitas vezes confusos é importante!

> Na etapa da atividade antes mesmo de ler a documentação fui direto rodar o código...

O que eu encontrei foi somente um erro, e não era nem mesmo de sintaxe, apenas uma definição incorreta em método para o que a aplicação promovia, só existia o limitador final do método para o random, faltava definir o inicial, e além disso não considerava-se o próprio último número.

Esta Parte foi bem fácil na verdade, simplesmente adicionei mais um valor, mas minha percepção veio depois à respeito da não inclusão do valor necessário para o ```D6```

``` kt
"D6"   -> Random.nextInt(6)
```

foi para:

``` kt
"D6"   -> Random.nextInt(1, 7)
```

Ao fim desta etapa, me atentei à documentação ```Qual é o próximo passo?``` e então observei a necessidade de mais opções, e novamente acabou sendo um movimento natural, foi no primeiro momento que já implementei o D10 para testar inicialmente no switch case:


``` kt
onClick = {
    val valorSorteado = when (dadoSelecionado) {
        "D6"   -> Random.nextInt(1, 7)
        "D10"  -> Random.nextInt(1, 11)
    }
  }
```

Neste caso eu já tinha o "bizu" de colocar uma unidade a mais e iniciar no número 1. Compilei e.... ```Vish, n funfou``` 
o problema é que eu não havia alterado	a lista que estava definida um pouco mais acima! Só existia um valor possível para dados, o D6, e assim corrigi.

Dali em diante o trabalho ficou mais manual, copei e colei quase as mesmas especificações para cada valor de dado (D6, D10, D20, D100).

### Ir Além!

> Vamos Agora adicionar algumas imagenszinhas para representar os nossos valores de output!

Pra deixar o app com uma cara mais legal (como sugerido pelo Mumu): eu não queria que ele mostrasse só o texto do resultado. A ideia era que aparecesse a imagem do dado de acordo com o que fosse sorteado.

Como eu fazia ideia de como adicionar uma imagem no celular, fiz o que todo dev faz: joguei no Google "how to add images to apps on android studio". O primeiro link já me mandou pra uma documentação da própria Google (tá lá nas fontes) que ensinava a usar o componente Image.
Aprendi que pra isso eu precisava de três coisas principais:

1. O arquivo da imagem: Logo peguei uma imagem do google e cortei no Canva, coloquei as imagens na pasta app/src/main/res/drawable a partir do import no próprio android studio e vi que só de estarem lá, o Android Studio cria uma referência automática pra elas usando o ```R.drawable.{nome}```.

2. Uma variável pra controlar o estado: Criei a ```var img by remember { mutableStateOf<Int?>(null) }```. Aqui tem um ponto importante: O remember. Sem ele, toda vez que eu desse um "refresh" na tela, ele ia esquecer qual imagem tava lá e ia resetar tudo pro valor inicial, o remember serve justamente pra "salvar" o valor na memória. ```O que foi um aprendizado bem importante para mim!```

3. O componente Image: Usei a função Image(), o contentDescription (que é tipo o "alt text") e o modifier pra eu conseguir dar um tapa no tamanho dela usando o .size(64.dp).
No fim, o código pra mostrar a imagem ficou assim:

``` kt
img?.let { imagemId ->
    Image(
        painter = painterResource(imagemId),
        contentDescription = "O valor que saiu foi",
        modifier = Modifier.size(64.dp)
    )
}
```

Dessa forma, a imagem só aparece se o img não for nulo (um dos casos que deixei no switch).


### fontes:

https://developer.android.com/codelabs/basic-android-kotlin-compose-add-images?hl=pt-br#0
https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.random/-random/