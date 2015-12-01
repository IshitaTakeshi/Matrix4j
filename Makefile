JFLAGS =
JC = javac
.SUFFIXES: .java .class

CLASSES = \
	    matrix/ArrayUtils.java \
	    matrix/Assert.java \
	    matrix/LinearAlgebra.java \
        matrix/Math.java \
        matrix/Matrix.java \
        matrix/TestArrayUtils.java \
        matrix/TestLinearAlgebra.java \
        matrix/TestMatrix.java \
        matrix/TestVector.java \
        matrix/Vector.java

default: all

all:
	$(JC) $(JFLAGS) $(CLASSES)

clean:
	$(RM) *.class
