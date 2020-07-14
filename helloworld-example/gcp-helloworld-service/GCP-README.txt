
https://rieckpil.de/howto-deploy-a-spring-boot-application-to-gke/
https://github.com/rieckpil/blog-tutorials/tree/master/deploy-spring-boot-to-gke

-----------------------
Create cluster manually on console

docker build -t gcp-helloworld-service:v01
docker tag gcp-helloworld-service:v01 gcr.io/msakubernetes/gcp-helloworld-service:v01
gcloud auth configure-docker
docker push gcr.io/msakubernetes/gcp-helloworld-service:v01

gcloud container clusters get-credentials msa-cluster --zone us-west1-a --project msakubernetes
kubectl apply -f config/deployment.yaml

http://34.82.193.85/api/helloservice/mrudul123

gcloud deployment-manager deployments delete msa-cluster
gcloud deployment-manager deployments delete msa-gke

-----------------------
GCP with IaaS

gcloud deployment-manager deployments create msa-cluster --config cluster.yaml

gcloud container clusters get-credentials msa-cluster-my-cluster --zone us-west1-a --project msakubernetes
gcloud deployment-manager deployments create msa-deployment --config deployment.yaml

gcloud deployment-manager deployments delete msa-deployment
gcloud deployment-manager deployments delete msa-cluster

http://34.82.193.85/api/helloservice/mrudul123

