package com.formacaoandroidstudio.fragments.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.formacaoandroidstudio.fragments.R;
import com.formacaoandroidstudio.fragments.fragment.ContatosFragment;
import com.formacaoandroidstudio.fragments.fragment.ConversasFragment;

public class MainActivity extends AppCompatActivity {

    private Button buttonConversa, buttonContato;

    // Criando objeto Fragment
    private ConversasFragment conversaFragment;
    private ContatosFragment contatosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Capturando os ids dos componentes do button
        buttonContato = findViewById(R.id.buttonContatos);
        buttonConversa = findViewById(R.id.buttonConversas);

        // Remover sombra do ActionBar
        getSupportActionBar().setElevation(0);

        // Instanciando o Fragment
        conversaFragment = new ConversasFragment();

        // Configurar objeto para o fragmento - começa a transação
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Vamos adicionar o transaction que recebe dois parâmetros:
        // 1 - id do componente frame onde vai ser adicionado o fragment | 2 - Fragmento que você quer exibir
        transaction.add(R.id.frameConteudo, conversaFragment);

        // Comita a transação - encerra a transação
        transaction.commit();

        // Evento ao clicar o botão de contato
        buttonContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contatosFragment = new ContatosFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                // Vamos fazer um replace (que add ou substitui o fragment)
                transaction.replace(R.id.frameConteudo, contatosFragment);
                transaction.commit();
            }
        });

        // Evento ao clicar o botão de conversa
        buttonConversa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                conversaFragment = new ConversasFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, conversaFragment);
                transaction.commit();
            }
        });

    }
}

/*
* Anotações:
*
* -> Fragments (fragmentos)
Os fragments representam um comportamento ou uma porção de interface com o usuário na activity. Ou seja, é possível criar vários fragments dentro da mesma
interface. Permite o reuso de código e interfaces mais dinâmicas.

Um Fragment representa o comportamento ou uma parte da interface do usuário em um FragmentActivity. É possível combinar vários fragmentos em uma única atividade
para criar uma IU de vários painéis e reutilizar um fragmento em diversas atividades. Você pode imaginar um fragmento como uma seção modular de uma atividade,
que tem o próprio ciclo de vida, recebe os próprios eventos de entrada e que pode ser adicionada ou removida durante a execução da atividade
(uma espécie de “subatividade” que pode ser reutilizada em diferentes atividades).

Um fragmento deve sempre ser hospedado em uma atividade e o ciclo de vida dele é diretamente impactado pelo ciclo de vida da atividade do host.
Por exemplo, quando a atividade é pausada, todos os fragmentos também são e, quando a atividade é destruída, todos os fragmentos também são. No entanto,
enquanto uma atividade estiver em execução (estiver no estado do ciclo de vida retomado), é possível processar cada fragmento independentemente,
como adicioná-los ou removê-los. Ao realizar tal transação com fragmentos, também é possível adicioná-los a uma pilha de retorno que é gerenciada pela
atividade — cada entrada da pilha de retorno na atividade é um registro da transação de fragmento que ocorreu. A pilha de retorno permite que o usuário
reverta uma transação de fragmento (navegue para trás), pressionando o botão Voltar.

Ao adicionar um fragmento como parte do layout da atividade, ele se encontrará em um ViewGroup dentro da hierarquia de visualizações, e o fragmento definirá o
próprio layout da exibição. É possível inserir um fragmento no layout, declarando-o no arquivo de layout da atividade como um elemento <fragment>, ou no
código do aplicativo, adicionando-o a um ViewGroup atual.

-> Filosofia do Projeto

O Android introduziu os fragmentos no Android 3.0 (API de nível 11) principalmente para suportar mais projetos de IU flexíveis e dinâmicos em telas grandes,
como em tablets. Como a tela de um tablet é muito maior que a de um celular, há mais espaço para combinar e alternar componentes da IU. Os fragmentos permitem
tais projetos sem haver a necessidade de gerenciar alterações complexas na hierarquia de visualizações. Ao dividir o layout de uma atividade em fragmentos, é
possível modificar a aparência da atividade em ambiente de execução e preservar essas alterações na pilha de retorno que é gerenciada por essa atividade.
Agora estão amplamente disponíveis por meio da biblioteca de suporte a fragmentos.

Por exemplo, um aplicativo de notícias pode usar um fragmento para exibir uma lista de artigos na esquerda e outro fragmento para exibir um artigo na
direita — ambos os fragmentos aparecem em uma atividade, lado a lado. Cada fragmento tem o próprio conjunto de métodos de callback do ciclo de vida e lida
com os próprios eventos de entrada do usuário. Portanto, em vez de usar uma atividade para selecionar um artigo e outra atividade para lê-lo, o usuário pode
selecionar um artigo e lê-lo por completo dentro da mesma atividade.

Você deve projetar cada fragmento como um componente modular e reutilizável da atividade. Ou seja, como cada fragmento define o próprio layout e comportamento
com os próprios callbacks do ciclo de vida, é possível incluir um fragmento em várias atividades para poder projetá-lo para reutilização e evitar o tratamento
direto de um fragmento em outro fragmento. Isso é especialmente importante porque um fragmento modular permite alterar as combinações de fragmentos para tamanhos
de tela diferentes. Ao projetar o aplicativo para ser compatível com tablets e celulares, você poderá reutilizar os fragmentos em diferentes configurações de layout
para otimizar a experiência do usuário com base no espaço de tela disponível. Por exemplo, em um celular, talvez seja necessário separar os fragmentos para fornecer
uma IU de painel único quando mais de um não se encaixar dentro da mesma atividade.

Por exemplo — continuando com o exemplo do aplicativo de notícias —, o aplicativo pode incorporar dois fragmentos na atividade A quando executado em um dispositivo
do tamanho de um tablet. No entanto, em uma tela de tamanho de celular, não há espaço suficiente para ambos os fragmentos, então a atividade A inclui somente o
fragmento da lista de artigos e, quando o usuário seleciona um artigo, ele inicia a atividade B, que inclui o segundo fragmento para ler o artigo. Portanto, o
aplicativo é compatível com tablets e celulares por meio da reutilização dos fragmentos em combinações diferentes.

-> Criação de um fragmento

Para criar um fragmento, é preciso criar uma subclasse de Fragment (ou usar uma subclasse existente dele).
A classe Fragment tem um código que é muito parecido com o de uma Activity. Ele contém métodos de callback semelhantes aos de uma atividade, como onCreate(),
onStart(), onPause() e onStop(). Na verdade, caso esteja convertendo um aplicativo do Android existente para usar os fragmentos, basta mover o código dos
métodos de retorno de chamada da atividade para os respectivos métodos de callback do fragmento.

Geralmente, deve-se implementar pelo menos os seguintes métodos de ciclo de vida:

   => onCreate()
      O sistema o chama ao criar o fragmento. Dentro da implementação, deve-se inicializar os componentes essenciais do fragmento que se deseja reter quando o
      fragmento é pausado ou interrompido e, em seguida, retomado.

   => onCreateView()
      O sistema chama isso quando é o momento de o fragmento desenhar a interface do usuário pela primeira vez. Para desenhar uma IU para o fragmento, você
      deve retornar uma View deste método, que é a raiz do layout do fragmento. É possível retornar como nulo se o fragmento não fornecer uma IU.

   => onPause()
      O sistema chama esse método como o primeiro indício de que o usuário está saindo do fragmento (embora não seja sempre uma indicação de que o fragmento
      está sendo destruído). É quando geralmente se deve confirmar qualquer alteração que deva persistir além da sessão do usuário atual (porque o usuário
      pode não retornar).

A maioria dos aplicativos deve implementar pelo menos três destes métodos para cada fragmento, mas há vários outros métodos de callback que você deve usar
para lidar com diversos estágios do ciclo de vida do fragmento. Todos os métodos de callback do ciclo de vida são abordados com mais detalhes na seção
Processamento do ciclo de vida dos fragmentos.

Há também algumas subclasses que você pode querer estender em vez da classe Fragment de base:

   => DialogFragment
      Exibe uma caixa de diálogo flutuante. Usar essa classe para criar uma caixa de diálogo é uma boa alternativa para usar métodos auxiliares das caixas de
      diálogo na classe Activity, pois é possível incorporar uma caixa de diálogo de fragmento na pilha de retorno dos fragmentos gerenciados pela atividade,
      permitindo que o usuário retorne a um fragmento dispensado.

   => ListFragment
      Exibe uma lista de itens gerenciados por um adaptador (como um SimpleCursorAdapter), semelhante a ListActivity. Ele fornece vários métodos para gerenciar
      uma visualização de lista, como o callback onListItemClick() para lidar com eventos de clique. O método preferido de exibição de uma lista é usar RecyclerView
      em vez de ListView. Neste caso, você precisaria criar um fragmento que inclui um RecyclerView no layout. Acesse Criar uma lista com RecyclerView para saber
      como fazer isso.

   => PreferenceFragmentCompat
      Exibe uma hierarquia de objetos Preference como uma lista. Isso é usado para criar uma tela de configurações para seu aplicativo.

Fonte: https://developer.android.com/guide/components/fragments?hl=pt-br
*
* Para criar um fragment:
* Com o botão direito do mouse em cima do pacote vai em:
* new > Fragment > Fragment (Blank) [Existe várias opções de fragmets] > Fragment Nome
* */
