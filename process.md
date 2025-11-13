# Process Documentation: Software Development Life Cycle (SDLC)

## Methodology: Agile Development

The **Event Management System** project follows the **Agile Software Development Life Cycle (SDLC)** methodology. Agile emphasizes flexibility, collaboration, and iterative progress through continuous feedback and improvement.

---

## 1. Project Vision

The goal of this project is to develop a **Flask-based Event Management System** that enables users to register, manage, and participate in events efficiently. The system provides an intuitive user interface, secure authentication, and a scalable backend architecture.

---

## 2. Agile Framework Overview

The team adopts the **Scrum framework** within Agile to structure development into short, focused iterations known as **sprints**. Each sprint delivers functional increments of the application that are reviewed and refined.

### Key Roles:

* **Product Owner:** Defines the project goals, features, and priorities.
* **Scrum Master:** Facilitates the Agile process, removes blockers, and ensures adherence to principles.
* **Development Team:** Implements features, writes tests, and maintains quality standards.

### Sprint Duration:

Each sprint lasts **2 weeks**, followed by a sprint review and retrospective.

---

## 3. SDLC Phases under Agile

### **Phase 1: Planning & Requirement Analysis**

* Conduct brainstorming sessions with stakeholders.
* Define **user stories** and prioritize them in the **product backlog**.
* Establish acceptance criteria for each feature.
* Identify tools and technologies (Flask, Python, GitHub Actions, etc.).

### **Phase 2: Design**

* Create wireframes and UI flow for event registration and management.
* Design database schema and define relationships.
* Establish Flask Blueprint structure for modular development.
* Define CI/CD pipeline in `.github/workflows` for automated testing and deployment.

### **Phase 3: Development (Sprint Execution)**

* Break user stories into smaller development tasks.
* Follow best practices: modular code, comments, and version control.
* Conduct daily stand-ups to track progress and remove blockers.
* Update the sprint board to reflect task status.

### **Phase 4: Testing**

* Write and run unit tests within the `tests/` directory.
* Implement automated testing via GitHub Actions.
* Perform functional and integration tests to ensure reliability.
* Conduct peer code reviews before merging pull requests.

### **Phase 5: Deployment**

* Deploy each stable build to a testing or staging environment.
* Validate that CI/CD workflows execute successfully.
* Prepare for production deployment after successful sprint review.

### **Phase 6: Review & Retrospective**

* Evaluate sprint deliverables against the acceptance criteria.
* Collect feedback from stakeholders and team members.
* Identify process improvements for future sprints.

### **Phase 7: Maintenance & Continuous Improvement**

* Fix reported bugs and optimize performance.
* Continuously add new features based on evolving user needs.
* Refactor existing code for scalability and maintainability.

---

## 4. Agile Artifacts

* **Product Backlog:** List of all desired features and improvements.
* **Sprint Backlog:** Set of items selected for implementation in the current sprint.
* **Burndown Chart:** Visual representation of work completed over time.
* **Increment:** A potentially shippable product at the end of each sprint.

---

## 5. Tools and Technologies

* **Framework:** Flask (Python)
* **Version Control:** Git & GitHub
* **CI/CD:** GitHub Actions
* **Testing:** Pytest
* **Project Management:** GitHub Projects / Trello / Notion
* **Database:** SQLite / PostgreSQL (based on environment)

---

## 6. Continuous Integration & Delivery (CI/CD)

* GitHub Actions automatically runs test suites on every push and pull request.
* Deployment workflows are configured to ensure smooth delivery and minimal downtime.
* Code quality is enforced using linting tools and automated test checks.

---

## 7. Communication and Collaboration

* **Daily Stand-ups:** Discuss progress, challenges, and plans.
* **Weekly Reviews:** Demonstrate new features to stakeholders.
* **Retrospectives:** Reflect on the sprint’s successes and areas for improvement.

---

## 8. Summary

The Agile SDLC ensures that the Event Management System evolves through continuous feedback and iteration. This approach promotes adaptability, faster delivery, and higher software quality while ma
