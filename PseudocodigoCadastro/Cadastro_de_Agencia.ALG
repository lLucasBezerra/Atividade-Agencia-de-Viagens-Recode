Algoritmo "Cadastro_de_Agencia"

// Professor   : Artur Brandt
// Descri��o   : Criar um Cadastro de uma agencia de viagens
// Autor(a)    : Jos� Lucas Bezerra da Silva

Var
        //clientes e destino
     cliente: vetor[1..50, 1..5] de caractere
     destino: vetor[1..50, 1..4] de caractere
     
       //vetores de mensagens
     msgClie: vetor[1..5] de caractere
     msgDest: vetor[1..4] de caractere

     menu: inteiro                      //menu de escolhas
     opcao: inteiro                  // vari�vel de repeti��o principal
     
     Quant_cliente: inteiro      //contadores
     x, y: inteiro               //cadastros
     w, z: inteiro
     a, b: inteiro               //consultas
     c, d: inteiro
     cpf: caractere
procedimento consulta(parametro: caractere)
 inicio
   para x de 1 ate quant_Cliente passo 1 faca

     se(parametro = cliente[x, 3]) entao
       escreval("Cliente de n� ", x, " --------")
       escreval("")
       
       para y de 1 ate 5 passo 1 faca
         escreval(msgClie[y], cliente[x,y])
       fimpara

       escreval("")
       escreval("Destino do cliente ", x, " --------")
       escreval("")
       
       para y de 1 ate 4 passo 1 faca
            escreval(msgDest[y], destino[x,y])
       fimpara
       
     fimse

   fimpara
fimprocedimento
Inicio

      opcao <- 1
      escreval("---- Quantos clientes voc� deseja cadastrar? -----")
        leia(quant_cliente)

   repita     //INICIO DO LOOP PRINCIPAL ----------------------
    limpatela
   //menu -----------------------------------------------------
       escreval("====---- MENU PARA CADASTRO DA AGENCIA ----====")
       escreval("1- Cadastrar o cliente")
       escreval("2- Cadastro do destino")
       escreval("3- Consulta de todos os clientes")
       escreval("4- Consulta de todos os destinos")
       escreval("5- Consulta individual")
       escreval("ESC para sair")
         leia(menu)
   //----------------------------------------------------------
   
   
       escolha menu

       caso 1
   //1- Cadastro de clientes ----------------------

             // mensagens para cadastro
       msgClie[1] <- "digite seu nome: "
       msgClie[2] <- "digite seu sobrenome: "
       msgClie[3] <- "digite seu CPF (apenas n�mero): "
       msgClie[4] <- "digite o nome da cidade em que esta: "
       msgClie[5] <- "digite o nome do Estado da cidade: "
        limpatela
   
           escreval("====--- CADASTRO DE CLIENTE ---====")
           escreval("")
          // escreval("Quantos clientes voc� quer cadastrar")
            //leia(quant_cliente)
           para x de 1 ate quant_cliente passo 1 faca
                escreval(x, "� cliente-----------")

                para y de 1 ate 5 passo 1 faca
                    escreval(msgClie[y])
                      leia(cliente[x, y])
                fimpara
                
           fimpara
   //-----------------------------------------------------------


       caso 2
   //cadastro de destino ---------------------------------------
       limpatela

       msgDest[1] <- "Digite o pa�s do seu destino: "
       msgDest[2] <- "Digite a cidade do seu destino: "
       msgDest[3] <- "Digite a quantidade de dias a viagem ir� durar: "
       msgDest[4] <- "Digite a data que queira marcar a viagem(dd/mm/aaaa): "

           escreval("====--- CADASTRO DE DESTINO ---====")
           escreval("")
           para w de 1 ate quant_cliente passo 1 faca
                escreval("cadastro do destino do", w, "� cliente-----------")

                para z de 1 ate 4 passo 1 faca
                    escreval(msgDest[z])
                      leia(destino[w, z])
                fimpara
            
           fimpara
   //-----------------------------------------------------------
   
   
       caso 3
   //consultar informa��es dos clientes --------------------------
       limpatela
           escreval("===--- CONSULTANDO INFORMA��ES DO CLIENTE ---===")
           escreval("")
           para a de 1 ate quant_cliente faca
                escreval(a, "� cliente-----------")

                para b de 1 ate 5 faca
                    escreval(cliente[a,b])
                fimpara
        
                escreval("-------------------------")
           fimpara

   //-------------------------------------------------------------
   
   
       caso 4
   //consultar infroma��es dos destinos --------------------------
       limpatela
           escreval("===--- CONSULTANDO INFORMA��ES DO DESTINO ---===")
           escreval("")
           para c de 1 ate quant_cliente faca
                escreval(c, "� cliente-----------")

                para d de 1 ate 4 faca
                    escreval(destino[c,d])
                fimpara
           
                escreval("-------------------------")
           fimpara
    //-------------------------------------------------------------



   //Consulta individual ------------------------------------------
        caso 5
        limpatela
            escreval("----- CONSULTANDO CLIENTE INDIVIDUALMENTE -----")

            escreva("Informe o CPF para buscar o cliente ")
            leia(cpf)
            consulta(cpf)
   //-------------------------------------------------------------

       fimescolha
       
       
       
   escreval("deseja continuar? 1-SIM / 2-N�O")
   leia(opcao)  // Precisa ser com letra mai�scula
   
   ate (opcao = 2) //FIM DO LOOP PRINCIPAL --------------------
   
Fimalgoritmo