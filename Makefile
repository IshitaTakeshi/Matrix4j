JFLAGS =
JC = javac
JAVA = java

.SUFFIXES: .java .class

CLASSES = \
	    matrix4j/core/ArrayUtils.java \
	    matrix4j/core/Assert.java \
	    matrix4j/core/LinearAlgebra.java \
        matrix4j/core/Math.java \
        matrix4j/core/Matrix.java \
        matrix4j/core/TestArrayUtils.java \
        matrix4j/core/TestLinearAlgebra.java \
        matrix4j/core/TestMatrix.java \
        matrix4j/core/TestVector.java \
        matrix4j/core/Vector.java \
        matrix4j/core/VectorIO.java \
        matrix4j/core/TestVectorIO.java \
        matrix4j/core/RandomArray.java \
        matrix4j/linalg/Eigen.java \
        matrix4j/linalg/TestEigen.java

default: all

all:
	$(JC) $(JFLAGS) $(CLASSES)

test:
	$(JAVA) matrix4j.core.TestMatrix
	$(JAVA) matrix4j.core.TestVector
	$(JAVA) matrix4j.core.TestArrayUtils
	$(JAVA) matrix4j.core.TestVectorIO
	$(JAVA) matrix4j.linalg.TestEigen

clean:
	rm matrix4j/core/*.class
