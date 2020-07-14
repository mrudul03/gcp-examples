

docker build -t demo-product-service:v01 .
docker tag demo-product-service:v01 gcr.io/msa-gke-demo/demo-product-service:v01
gcloud auth configure-docker
docker push gcr.io/msa-gke-demo/demo-product-service:v01

HTTPS_PROXY=localhost:8888 kubectl get pods --all-namespaces
alias k="HTTPS_PROXY=localhost:8888 kubectl"

k get namespaces

gcr.io/start-up-demo-123/demo-product-service:v01

k apply -f gcp-deployment.yaml
k apply -f gcp-service.yaml

k describe pod product-dep-8bf85d8b-wrbdj

gcloud container images list-tags gcr.io/start-up-demo-123/demo-product-service:v01

k delete deployment product-dep
k delete service product-svc

gcp gke pod deployment error pull access denied gcr image
gcp deployment error pull access denied for image in gcr