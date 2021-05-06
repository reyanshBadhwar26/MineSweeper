
echo "compile"
javac -classpath . utilities/*.java backgrounds/*.java sprites/*.java universes/*.java  gui/*.java main/*.java -d ./bin
echo "run Main.java"
java -classpath .:/run_dir/junit-4.12.jar:/run_dir/hamcrest-core-1.3.jar:/run_dir/json-simple-1.1.1.jar:./bin Main
