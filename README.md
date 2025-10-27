
# Sistema de Cardápio do RU

Sistema desenvolvido em Java para gerenciar o cardápio semanal do Restaurante Universitário.

## Como Compilar

```bash
javac *.java
```

## Como Executar

```bash
java SistemaRU
```

## Estrutura do Sistema

### Classes Principais:

1. **Alimento**: Representa os itens básicos (saladas, pratos principais, acompanhamentos)
2. **Refeicao**: Combina os três tipos de alimentos em uma refeição completa
3. **Menu**: Associa dia, turno e refeição
4. **SistemaRU**: Classe principal com toda a lógica de interação

### Funcionalidades:

1. **Cadastrar Alimentos**
   - Saladas
   - Pratos principais
   - Acompanhamentos

2. **Montar Refeições**
   - Combinar salada + prato principal + acompanhamento
   - Sistema valida se os tipos estão corretos

3. **Criar Menu do Dia**
   - Associar refeições a dias e turnos
   - Almoço e Jantar disponíveis

4. **Visualizar Cardápio Semanal**
   - Mostra organizado por dia da semana
   - Exibe almoço e jantar de cada dia

## Exemplo de Uso

### Passo 1: Cadastrar Alimentos
- Cadastre algumas saladas (ex: Salada Verde, Salada de Tomate)
- Cadastre pratos principais (ex: Frango Grelhado, Carne de Panela)  
- Cadastre acompanhamentos (ex: Arroz, Feijão, Farofa)

### Passo 2: Montar Refeições
- Crie refeições combinando os alimentos cadastrados
- Exemplo: "Almoço Básico" com Salada Verde + Frango + Arroz

### Passo 3: Criar Menus
- Associe as refeições aos dias da semana
- Escolha o turno (almoço ou jantar)

### Passo 4: Visualizar
- Use a opção 4 para ver o cardápio completo da semana

## Observações

- O sistema valida se os alimentos têm o tipo correto ao montar refeições
- Pode cadastrar múltiplos menus para o mesmo dia (almoço e jantar)
- Os dados ficam em memória durante a execução


