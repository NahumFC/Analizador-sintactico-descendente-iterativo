import java.util.List;
import java.util.Stack;
import java.util.Arrays;

public class ASDI implements Parser{

    private int i = 0;
    private boolean hayErrores = false;
    private final List<Token> tokens;
    String [][] TablaAS =
    { {"No terminal",     "select",             "from",      "distinct",      "*",       ",",        "id",        ".",       "$" },
      {     "Q",        "select D from T",       "",             "",           "",       "",          "",          "",       ""  },             
      {     "D",             "",                 "",         "distinct P",     "P",      "",          "P",         "",       ""  },
      {     "P",             "",                 "",             "",           "*",      "",          "A",         "",       ""  },  
      {     "A",             "",                 "",             "",           "",       "",        "A2 A1",       "",       ""  }, 
      {     "A1",            "",                 "e",            "",           "",      ", A",         "",         "",       ""  },
      {     "A2",            "",                 "",             "",           "",       "",        "id A3",       "",       ""  },
      {     "A3",            "",                 "e",            "",           "",       "e",          "",       ". id",     ""  },
      {     "T",             "",                 "",             "",           "",       "",        "T2 T1",       "",       ""  },
      {     "T1",            "",                 "",             "",           "",      ", T",         "",         "",       "e" },
      {     "T2",            "",                 "",             "",           "",       "",        "id T3",       "",       ""  },                                                        
      {     "T3",            "",                 "",             "",           "",       "e",        "id",         "",       "e" }
    };

    public ASDI(List<Token> tokens){
        this.tokens = tokens;
    }

    @Override
    public boolean parse() {

        String entrada = "";
        int j = 0;
        Stack <String> pila = new Stack <String>();
        pila.push("$");
        pila.push("T");
        pila.push("from");
        pila.push("D");
        pila.push("select");

        while( !pila.empty() ){
              

            if(tokens.get(i).tipo == TipoToken.IDENTIFICADOR )
                entrada = "id";
            else 
                entrada = tokens.get(i).lexema;


            if (pila.peek().equals(entrada)){
                pila.pop();
                i++;
            }
            else if( esTerminal(pila.peek()) ){
                hayErrores = true;
                break;
            }
            else if( !esTerminal(pila.peek()) && 
                     TablaAS[buscaNoTerminal(TablaAS, pila.peek() )][buscaTerminal(TablaAS, entrada )].equals("") ){
                hayErrores = true;
                break;
            }else{
                String[] producciones = TablaAS[buscaNoTerminal(TablaAS, pila.peek() )][buscaTerminal(TablaAS, entrada )].split(" "); 
                j = producciones.length;
                pila.pop();
                while(j>0){
                    if(!producciones[j-1].equals("e"))
                        pila.push(producciones[j-1]);
                    j--;
                }
            }
        }

        if( pila.empty() && tokens.get(i-1).tipo == TipoToken.EOF && !hayErrores){
            System.out.println("Consulta correcta");
            return  true;
        }else {
            System.out.println("Consulta Incorrecta");
        }
        return false;
    }

}

