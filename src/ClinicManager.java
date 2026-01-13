public class ClinicManager {
    public static final String MIN_ID = "";
    public static final String MAX_ID = "\uFFFF\uFFFF\uFFFF\uFFFF";
    public static final int MIN_num = -1;
    public static final int MAX_num = Integer.MAX_VALUE;
    
    private DVNTreeS<Doctor> doctorsTree;
    private DVNTreeI<Doctor> popularityTree;
    private DVNTreeS<Patient> patients;
    private int globalInsertionTime = 0;

    public ClinicManager() {
        doctorsTree = new DVNTreeS<>(MIN_ID, MAX_ID);
        popularityTree = new DVNTreeI<>(MIN_num, MAX_num);
        patients = new DVNTreeS<>(MIN_ID,MAX_ID);
    }

    public void doctorEnter(String doctorId) {
        if(doctorsTree.search(doctorsTree.getRoot(), doctorId) != null){
            throw new IllegalArgumentException();
        }else{
            Doctor d = new Doctor(doctorId, MIN_num, MAX_num);
            Node<Doctor> dNode = new Node<>(d, d.getId(), 0, globalInsertionTime++);
            doctorsTree.insert(dNode);
            popularityTree.insert(dNode);
        }
    }

    public void doctorLeave(String doctorId) {
        Node<Doctor> dNode = doctorsTree.search(doctorsTree.getRoot(), doctorId);
        if(dNode == null || dNode.getValue() != 0){
            throw new IllegalArgumentException();
        } else{
            doctorsTree.delete(dNode);
            popularityTree.delete(dNode);
        }
    }
    public void patientEnter(String doctorId, String patientId) {
        if (doctorsTree.search(doctorsTree.getRoot(), doctorId) == null ||
                patients.search(patients.getRoot(), patientId) != null){
            throw new IllegalArgumentException();
        } else {
            Doctor d = doctorsTree.search(doctorsTree.getRoot(), doctorId).getPerson();
            Node<Doctor> dNode = doctorsTree.search(doctorsTree.getRoot(), doctorId);
            Patient p = new Patient(patientId, d);
            Node<Patient> pNode = new Node<>(p, patientId, d.getAndPlusLastestNum(), 0);
            patients.insert(pNode);
            d.getQueue().insert(pNode);
            if(dNode.getValue() == 0){
                dNode.getPerson().setNextPatientId(patientId);
            }
            dNode.setValue(dNode.getValue()+1);
            updatePopularityTree(dNode);
        }
    }

    public String nextPatientLeave(String doctorId) {
        Node<Doctor> doctorNode = doctorsTree.search(doctorsTree.getRoot(), doctorId);
        if (doctorNode == null) {
            throw new IllegalArgumentException();
        } else {
            Node<Patient> pNode = doctorNode.getPerson().getQueue().minimum();
            if (pNode == null){
                throw new IllegalArgumentException();
            }
            doctorNode.getPerson().getQueue().delete(pNode);
            patients.delete(pNode);
            doctorNode.setValue(doctorNode.getValue()-1);
            updatePopularityTree(doctorNode);
            if (doctorNode.getValue() == 0) {
                doctorNode.getPerson().setNextPatientId(null);
            } else{
                Node<Patient> nextPatientNode = doctorNode.getPerson().getQueue().minimum();
                doctorNode.getPerson().setNextPatientId(nextPatientNode.getPerson().getPatientId());
            }
            return pNode.getPerson().getPatientId();
        }
    }

    public void patientLeaveEarly(String patientId) {
        Node<Patient> pNode = patients.search(patients.getRoot(), patientId);
        if(pNode == null){
            throw new IllegalArgumentException();
        } else {
            Node<Doctor> dNode = doctorsTree.search(doctorsTree.getRoot(), pNode.getPerson().getDoctorId());
            dNode.setValue(dNode.getValue()-1);
            dNode.getPerson().getQueue().delete(pNode);
            updatePopularityTree(dNode);
            if(dNode.getPerson().getNextPatientId() != null && dNode.getPerson().getNextPatientId().equals(patientId)){
                Node<Patient> nextPatientNode2 = dNode.getPerson().getQueue().minimum();
                if(nextPatientNode2 == null){
                    dNode.getPerson().setNextPatientId(null);
                }else{
                    dNode.getPerson().setNextPatientId(nextPatientNode2.getPerson().getPatientId());
                }
            }
            patients.delete(pNode);
        }

    }

    public int numPatients(String doctorId) {
        Node<Doctor> dNode = doctorsTree.search(doctorsTree.getRoot(), doctorId);
        if(dNode == null){
            throw new IllegalArgumentException();
        } else {
            return dNode.getValue();
        }
    }

    public String nextPatient(String doctorId) {
        Node<Doctor> dNode = doctorsTree.search(doctorsTree.getRoot(), doctorId);
        if(dNode == null){
            throw new IllegalArgumentException();
        }
        String p1 = dNode.getPerson().getNextPatientId();
        if (p1 == null){
            throw new IllegalArgumentException();
        }
        return p1;
    }

    public String waitingForDoctor(String patientId) {
        Node<Patient> pNode = patients.search(patients.getRoot(), patientId);
        if(pNode == null){
            throw new IllegalArgumentException();
        } else {
            return pNode.getPerson().getDoctorId();
        }
    }

    public int numDoctorsWithLoadInRange(int low, int high) {
        return popularityTree.numDoctorsWithLoadInRange(low, high);
    }

    public int averageLoadWithinRange(int low, int high) {
        return popularityTree.averageLoadWithinRange(low, high);
    }

    public void updatePopularityTree(Node<Doctor> dNode){
        globalInsertionTime++;
        dNode.setInsertionTime(globalInsertionTime);
        dNode.setSubtreeValueSum(dNode.getValue());
        popularityTree.delete(dNode);
        popularityTree.insert(dNode);
    }
}