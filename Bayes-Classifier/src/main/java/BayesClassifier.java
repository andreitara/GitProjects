import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.core.*;
import weka.experiment.InstanceQuery;

import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesSimple;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.bayes.AODE;
import weka.classifiers.bayes.AODEsr;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.classifiers.bayes.BayesianLogisticRegression;
import weka.classifiers.bayes.ComplementNaiveBayes;
import weka.classifiers.bayes.DMNBtext;
import weka.classifiers.bayes.HNB;
import weka.classifiers.bayes.NaiveBayesMultinomialUpdateable;
import weka.classifiers.bayes.WAODE;


public class BayesClassifier {

    private Classifier model;
    private Instances trainingSet;
    private Instances testingSet;
    private Evaluation evaluation;
    private FastVector predictions = new FastVector();
    private String database;

    public BayesClassifier(){}

    public void setModel(String model){
        if(model.equals("BayesNet"))
            this.model = new BayesNet();
        else if(model.equals("NaiveBayes"))
            this.model = new NaiveBayes();
        else if(model.equals("NaiveBayesSimple"))
            this.model = new NaiveBayesSimple();
        else if(model.equals("NaiveBayesUpdateable"))
            this.model = new NaiveBayesUpdateable();
        else if(model.equals("AODE"))
            this.model = new AODE();
        else if(model.equals("AODEsr"))
            this.model = new AODEsr();
        else if(model.equals("NaiveBayesMultinomial"))
            this.model = new NaiveBayesMultinomial();
        else if(model.equals("BayesianLogisticRegression"))
            this.model = new BayesianLogisticRegression();
        else if(model.equals("ComplementNaiveBayes"))
            this.model = new ComplementNaiveBayes();
        else if(model.equals("DMNBtext"))
            this.model = new DMNBtext();
        else if(model.equals("HNB"))
            this.model = new HNB();
        else if(model.equals("NaiveBayesMultinomialUpdateable"))
            this.model = new NaiveBayesMultinomialUpdateable();
        else if(model.equals("WAODE"))
            this.model = new WAODE();
        else
            this.model = new NaiveBayesUpdateable();
        this.model.setDebug(true);
    }

    public void setDatabase(String database){
        this.database = database;
    }

    public void setClassIndex(int index){
        trainingSet.setClassIndex(index);
        testingSet.setClassIndex(index);
    }

    public void setTrainingSetFromDatabase(String table, String username, String password) throws Exception {
        InstanceQuery query = new InstanceQuery();
        query.setUsername(username);
        query.setPassword(password);
        query.setQuery("select * from [" + database + "].[dbo].[" + table + "]");
        trainingSet = query.retrieveInstances();
    }

    public void setTestingSetFromDatabase(String table, String username, String password) throws Exception {
        InstanceQuery query = new InstanceQuery();
        query.setUsername(username);
        query.setPassword(password);
        query.setQuery("select * from [" + database +"].[dbo].[" + table + "]");
        testingSet = query.retrieveInstances();
    }

    public void classify() throws Exception {
        evaluation = new Evaluation(trainingSet);
        model.buildClassifier(trainingSet);
        evaluation.evaluateModel(model, testingSet);
        predictions.addElement(evaluation.predictions());

        System.out.println(model.toString());

        //double accuracy = calculateAccuracy(predictions);
        //System.out.println("Accuracy of " + model.getClass().getSimpleName() + ": "
        //        + String.format("%.2f%%", accuracy)
        //        + "\n---------------------------------");
    }

    public static double calculateAccuracy(FastVector predictions) {
        double correct = 0;

        for (int i = 0; i < predictions.size(); i++) {
            NominalPrediction np = (NominalPrediction) predictions.elementAt(i);
            if (np.predicted() == np.actual()) {
                correct++;
            }
        }

        return 100 * correct / predictions.size();
    }

    public String getModelString(){
        return model.toString();
    }

}