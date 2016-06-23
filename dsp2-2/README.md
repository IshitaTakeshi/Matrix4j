```
javac ../matrix4j/*/*.java *.java
java -cp ..:. DSP samp.bmp | sed -e 's/\[\|\]//g' > dct
python3 plot.py
```
