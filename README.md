Install Required Plugins
Go to Jenkins Dashboard → Manage Jenkins → Plugins → Available plugins.
Install:
Pipeline: Docker (docker-workflow plugin)
Docker Pipeline (if not included already)
Restart Jenkins after installation.
Without these, agent { docker { … } } won’t work.
