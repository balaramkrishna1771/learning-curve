import NewProject from "./components/NewProject.jsx";
import SideBar from "./components/SideBar.jsx";
import NoProjectSelected from "./components/NoProjectSelected.jsx";
import { useState } from "react";
import SelectedProject from "./components/SelectedProject.jsx";

function App() {
  const [createProject, setCreateProject] = useState({
    projectID: undefined,
    projects: []
  });

  function handleSelectedProject(id) {
    setCreateProject(prevState => {
      return {
        ...prevState,
        projectID: id
      }
    });
  }

  function handleCreateNewProject() {
    setCreateProject(prevState => {
      return {
        ...prevState,
        projectID: null
      }
    });
  }

  function handleNewProject(enteredNewProject) {
    setCreateProject(prevState => {
      const projectIDGen = Math.random();
      const newProject = {
        ...enteredNewProject,
        ID: projectIDGen
      };

      return {
        ...prevState,
        projectID: undefined,
        projects: [...prevState.projects, newProject]
      };
    });
  }

  function handleCancelNewProject() {
    setCreateProject(prevState => {
      return {
        ...prevState,
        projectID: undefined
      }
    });
  }
  function handleDeleteProject() {
    setCreateProject(prevState => {
      return {
        ...prevState,
        projectID: undefined,
        projects: prevState.projects.filter((project) => project.ID !== prevState.projectID)
      }
    });
  }
  const selectedProject = createProject.projects.find(project => project.ID === createProject.projectID);
  let content = <SelectedProject project={selectedProject} onDeleteProject={handleDeleteProject} />;

  if (createProject.projectID === null) {
    content = <NewProject onAdd={handleNewProject} onCancel={handleCancelNewProject} />;
  } else if (createProject.projectID === undefined) {
    content = <NoProjectSelected onStartAddProject={handleCreateNewProject} />;
  }
  return (
    <menu className="h-screen flex gap-8">
      <SideBar onStartAddProject={handleCreateNewProject}
        projects={createProject.projects}
        onSelectProject={handleSelectedProject} />
      {content}
    </menu>
  );
}

export default App;
