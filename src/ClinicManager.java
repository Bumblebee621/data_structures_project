public class ClinicManager {
    public static final String MIN_ID = "";
    public static final String MAX_ID = "\uFFFF\uFFFF\uFFFF\uFFFF";
    public static final int MIN_num = 0;
    public static final int MAX_num = 2 ^ 32 - 1; // Note: 2^32 in java is not 2 power 32. But keeping as is.
    
    private DVNTreeS<Doctor> doctorsTree;
    private DVNTreeI<Doctor> popularityTree;
    private DVNTreeS<Patient> patients;
    private int globalTimestamp = 0;

    public ClinicManager() {
        doctorsTree = new DVNTreeS<>(MIN_ID, MAX_ID);
        popularityTree = new DVNTreeI<>(MIN_num, MAX_num);
        patients = new DVNTreeS<>(MIN_ID,MAX_ID);
    }

    public void doctorEnter(String doctorId) {
        if(doctorsTree.search(doctorsTree.getRoot(), doctorId) != null){
            throw new IllegalArgumentException();
        }else{
            Doctor d = new Doctor(doctorId);
            // Insert to doctorsTree (String key). value is 0 (unused)
            DoubleValueNode<Doctor> ds = new DoubleValueNode<>(d, doctorId, 0);
            doctorsTree.insert(ds);

            // Insert to popularityTree (Integer key, with timestamp). identifier is null (unused)
            DoubleValueNode<Doctor> di = new DoubleValueNode<>(d, null, 0, globalTimestamp++);
            popularityTree.insert(di);
            //fuck
        }


        if (doctorsTree.search(doctorsTree.getRoot(), doctorId) == null) {
            Clinic c1 = new Clinic(doctorId);
            doctorsTree.insert(new StringDocNode<>(c1));
            
            IntDocNode<Doctor> intNode = new IntDocNode<>(c1);
            intNode.setInsertionTime(globalTimestamp++); // Renamed setter
            popularityTree.insert(intNode);
        } else {
            //throw exception
        }
    }

    public void doctorLeave(String doctorId) {
        StringDocNode<Doctor> docNode = (StringDocNode<Doctor>)doctorsTree.search(doctorsTree.getRoot(), doctorId);
        if (docNode == null) {
            //throw exeption
        } else if(docNode.getClinic().getAmountInLine() != 0){
            //throw exeption
        } else{
            doctorsTree.delete(docNode);
            // Search in popularityTree 
            IntDocNode<Doctor> intdocNode = findNodeInPopularityTree(docNode.getClinic().getAmountInLine(), doctorId);
            if (intdocNode != null) {
                popularityTree.delete(intdocNode);
            }
        }
    }
    
    // Helper to find node in popularityTree
    private IntDocNode<Doctor> findNodeInPopularityTree(int load, String doctorId) {
        // Start from min timestamp for this load
        DoubleValueNode<Doctor> node = popularityTree.findMinTimeStamp(load);
        while (node != null && node.getValue() == load) { // getNum() -> getValue()
            // Check if this node belongs to doctorId.
            if (node.getPerson() != null && node.getPerson().getId().equals(doctorId)) {
                return (IntDocNode<Doctor>) node; 
            }
            // Move to successor
            node = popularityTree.successor(node);
        }
        return null;
    }

    public void patientEnter(String doctorId, String patientId) {
        StringDocNode<Doctor> docNode = (StringDocNode<Doctor>)doctorsTree.search(doctorsTree.getRoot(), doctorId);
        StringPatientNode<Patient> pNode = (StringPatientNode<Patient>)patients.search(patients.getRoot(), patientId);
        if(docNode == null || pNode != null){
            //throw exception
        }else {
            Patient p1 = new Patient(patientId, docNode.getClinic().getDoc(), docNode.getClinic().getAmountInLine() + 1);
            StringPatientNode<Patient> sPatient = new StringPatientNode<Patient>();
            // ...
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
        return popularityTree.numDoctorsWithLoadInRange(low, high);
    }

    public int averageLoadWithinRange(int low, int high) {
        return popularityTree.averageLoadWithinRange(low, high);
    }

    public void updatePopularityTree(String doctorId){
        // ...
    }
}