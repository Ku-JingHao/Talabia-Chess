// Observer is an interface that provides the update method, which is implemented by classes that observe changes in the model.
// It follows the Observer design pattern.
//(Ku Jing Hao)
@FunctionalInterface 
interface Observer {
    void update();
}