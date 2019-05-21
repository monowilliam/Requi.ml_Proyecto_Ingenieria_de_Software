package Classes;

public class projectClass {
    private int IdProject,IdUser;
    private String nameProject,description;

    public int getIdProject(){return IdProject;}
    public void setIdProject(int IdProject){this.IdProject = IdProject;}
    public int getIdUser(){return IdUser;}
    public void setIdUser(int IdUser){this.IdUser = IdUser;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
