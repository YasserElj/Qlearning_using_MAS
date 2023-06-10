# Q-Learning with Multi-Agent System for Pathfinding
This repository contains the implementation of a pathfinding project that utilizes Q-learning with a Multi-Agent System (MAS) approach. The goal of this project is to find the optimal path in a given environment, taking into account multiple agents that are simultaneously navigating the same space.

## Q-Learning
The project leverages the power of Q-learning, a popular reinforcement learning algorithm, to guide the agents in discovering the most efficient path. Q-learning allows the agents to learn from their interactions with the environment by updating a Q-table, which stores action-value estimates. By continuously exploring and exploiting the environment, the agents gradually converge on the optimal path.

## Multi-Agent System 
The project incorporates multiple agents, enabling cooperative decision-making and exploration of the environment.

# JADE interface
![image](https://github.com/YasserElj/Qlearning_using_MAS/assets/61060853/7e641375-104e-45ed-8ccc-cb7b2ae69f3f)

# The Test
```java
public static int[][] GRID = new int[][]{
            {0,0,0,0,0,0},
            {0,0,0,0,0,-1},
            {0,0,0,-1,0,0},
            {0,0,0,0,0,0},
            {0,-1,0,-1,0,0},
            {-1,0,0,0,0,1}

    };

    public static int[][] ACTIONS = new int[][]{
            {0, -1}, // gauche
            {0, 1},  // droite
            {1, 0},  // bas
            {-1, 0}   // haut
    };
```

# Result

## Agent 1
![image](https://github.com/YasserElj/Qlearning_using_MAS/assets/61060853/e5be482f-081e-4233-aa26-631c93d96b99)


## Agent 4
![image](https://github.com/YasserElj/Qlearning_using_MAS/assets/61060853/da5df0f0-d264-4eb4-a990-67752749c1da)

## Qtable
![image](https://github.com/YasserElj/Qlearning_using_MAS/assets/61060853/2d29d5a7-bb03-4447-8cf5-4dbbbbff027d)

