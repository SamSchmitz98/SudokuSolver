JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
        $(JC) $(JFLAGS) $*.java

CLASSES = \
        src\sudoku\sudokuCell.java \
        src\sudoku\sudokuInterface.java \
        src\sudoku\sudokuMove.java \
        src\sudoku\sudokuSolver.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
        $(RM) *.class
