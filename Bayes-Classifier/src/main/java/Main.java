public class Main {

    public static void main(String[] args) throws Exception {

        GUI gui = new GUI();

        BayesClassifier bayesClassifier = new BayesClassifier();
        bayesClassifier.setDatabase("SGCDM");
        bayesClassifier.setModel("NaiveBayes");
        bayesClassifier.setTrainingSetFromDatabase("TrainingSet","sa","manager");
        bayesClassifier.setTestingSetFromDatabase("TestingSet","sa","manager");
        bayesClassifier.setClassIndex(2);
        bayesClassifier.classify();

    }
}
