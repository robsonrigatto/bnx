# Product delivery problem

Dúvidas sobre os resultados:

7 - Seguindo a amostra de teste do enunciado, há uma inconsistência nos caminhos em comparação com o resultado obtido. 
Isso ocorre pelo fato em que a rota está passando 2x no mesmo destino.
 
Enunciado:
 
    B -> A (1 stop)
  
    B -> D -> F -> A (3 stops)
  
    B -> A -> C -> B -> A (4 stops)
  
    B -> A -> D -> F -> A (4 stops)
  
    B -> D -> E -> C -> B -> A (5 stops)
  
    B -> D -> F -> C -> B -> A (5 stops)
 
Resultado obtido:
    
    B -> A (1 stop)
    
    B -> D -> F -> A (3 stops)
    
    
 11 e 12 - Provavelmente é o mesmo caso que ocorre no exercício 7 (2 ou mais vezes pelo mesma vértice)
 
 Enunciado:
 
    Output #11: 27
    
    Output #12: 137
    
Resultado obtido:

    OUTPUT #11 (A para B): 4
        
        A -> C -> B
        A -> D -> E -> C -> B
        A -> D -> F -> C -> B
        A -> D -> F -> E -> C -> B
    
    OUTPUT #12 (E para D): 2
    
        E -> C -> B -> D
        E -> C -> B -> A -> D
