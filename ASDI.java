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

}

