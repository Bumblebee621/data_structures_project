public class ClinicManagerDKN {
    public static final String MIN_ID = "";
    public static final String MAX_ID = "\uFFFF\uFFFF\uFFFF\uFFFF";
    public static final int MIN_num = 0;
    public static final int MAX_num = 2 ^ 32 - 1;
    private DKNTree<Doctor, String> doctorsTree;
    private DKNTree<Doctor, Integer> popularityTree;
    private DKNTree<Patient, String> patients;

    public ClinicManagerDKN() {
        doctorsTree = new DKNTree<>(MIN_ID, MAX_ID);
        popularityTree = new DKNTree<>(MIN_num, MAX_num);
        patients = new DKNTree<>(MIN_ID,MAX_ID);
    }

    public void doctorEnter(String doctorId) {
        if(doctorsTree.search(doctorsTree.getRoot(), doctorId) != null){
            throw new IllegalArgumentException();
        }else{
            DoubleKeyNode<>
            //fuck
        }


        if (doctorsTree.search(doctorsTree.getRoot(), doctorId) == null) {
            Clinic c1 = new Clinic(doctorId);
            doctorsTree.insert(new StringDocNode<>(c1));
            popularityTree.insert(new IntDocNode<>(c1));
        } else {
            //throw exception
        }
    }

    public void doctorLeave(String doctorId) {
        StringDocNode<String> docNode = (StringDocNode<String>)doctorsTree.search(doctorsTree.getRoot(), doctorId);
        if (docNode == null) {
            //throw exeption
        } else if(docNode.getClinic().getAmountInLine() != 0){
            //throw exeption
        } else{
            doctorsTree.delete(docNode);
            IntDocNode<Integer> intdocNode = (IntDocNode<Integer>)popularityTree.search(popularityTree.getRoot(), docNode.getClinic().getAmountInLine());
            popularityTree.delete(intdocNode);
        }
    }

    public void patientEnter(String doctorId, String patientId) {
        StringDocNode<String> docNode = (StringDocNode<String>)doctorsTree.search(doctorsTree.getRoot(), doctorId);
        StringPatientNode<String> pNode = (StringPatientNode<String>)patients.search(patients.getRoot(), patientId);
        if(docNode == null || pNode != null){
            //throw exception
        }else {
            Patient p1 = new Patient(patientId, docNode.getClinic().getDoc(), docNode.getClinic().getAmountInLine() + 1);
            StringPatientNode<String> sPatient = new StringPatientNode<String>();
        }
    }

    public String nextPatientLeave(String doctorId) {
        return null;
    }

    public void patientLeaveEarly(String patientId) {

    }

    public int numPatients(String doctorId) {
        return 0;
    }

    public String nextPatient(String doctorId) {
        return null;
    }

    public String waitingForDoctor(String patientId) {
        return null;
    }

    public int numDoctorsWithLoadInRange(int low, int high) {
        return 0;
    }

    public int averageLoadWithinRange(int low, int high) {
        return 0;
    }

    public void updatePopularityTree(String doctorId){
        IntDocNode<Integer> docNode = (IntDocNode<Integer>)popularityTree.search(popularityTree.getRoot(), doctorId);
        popularityTree.insert(docNode);
    }
}
