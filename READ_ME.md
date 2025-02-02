### This is my implementation of Conways Game Of Life.

The rules of the game can be read on [here] [Game_of_Life], but for this project I have implemented some
additional limitations to keep the game in line with this statement from the instructions: *"The grid you are creating has a fixed set of dimensions,
and everything outside the scope if its dimensions, cannot affect the cells on your grid."*. 

For my implementation, edge cells are 'terminal' states, meaning that any time the live cells touch the edge the game stops, you can progress
it by clicking 'Next' or 'Run' but this 'progress' will only be up to the relevant edge and relative to the cells within the edges.


## Instructions to run the game
There are 2 ways of running the game. 

You can either run it directly from the source code or using the .exe file provided.

The game uses parameters defined in the config.properties file which has to be in the same 
directory as your exe file when running it as an exe. The properties file gives it the Rows,
Columns, CellSize and StartState. The StartState is a list of the *First Generation* cell coordinates, these are the cells that initiate your
game pattern. The format of this is StartState=x1_y1,x2_y2,x3_y3...

Once the program is running, you can click 'Next' which performs the next step, or 'Run' which runs it to completion.
While the program runs, you can click 'Stop' to make it stop running.

[Game_of_Life]: https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life