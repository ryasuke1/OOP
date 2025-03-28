javadoc -d doc src/main/java/ru/nsu/khubanov/HeapSort.java src/main/java/ru/nsu/khubanov/Main.java
javac -d bin src/main/java/ru/nsu/khubanov/HeapSort.java src/main/java/ru/nsu/khubanov/Main.java
echo "Main-Class: ru.nsu.khubanov.Main" > manifest.txt
jar cfm HeapSortApp.jar manifest.txt -C bin .
java -cp bin ru.nsu.khubanov.Main ru.nsu.khubanov.HeapSort
