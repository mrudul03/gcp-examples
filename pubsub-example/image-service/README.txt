
Ref - https://github.com/mydeveloperplanet/mygcpsubplanet
https://medium.com/quintoandar-tech-blog/creating-google-cloud-pub-sub-publishers-and-subscribers-with-spring-cloud-gcp-part-2-362bd25eb03c

https://kubernetes.io/docs/reference/kubectl/cheatsheet/

https://medium.com/quintoandar-tech-blog/creating-google-cloud-pub-sub-publishers-and-subscribers-with-spring-cloud-gcp-part-1-setup-a96c53025fec
https://medium.com/quintoandar-tech-blog/creating-google-cloud-pub-sub-publishers-and-subscribers-with-spring-cloud-gcp-part-2-362bd25eb03c
https://github.com/fmachado091/spring-cloud-gcp-pubsub-example

https://cloud.google.com/solutions/integrating-microservices-with-pubsub
https://www.infoq.com/articles/spring-boot-tutorial/

*** https://cloud.google.com/kubernetes-engine/docs/tutorials/authenticating-to-cloud-platform
*** https://cloud.google.com/pubsub/docs/reference/libraries

image-service-0.0.1-SNAPSHOT

docker build -t image-service:v01 .
docker tag image-service:v01 gcr.io/msa-gke-demo/image-service:v01
gcloud auth configure-docker
docker push gcr.io/msa-gke-demo/image-service:v01

kubectl get namespaces
kubectl apply -f gcp-deployment.yaml

kubectl get pods
kubectl logs imgservice-dep-754475cdf7-524pc

kubectl describe pod imgservice-dep-754475cdf7-fvkmz

kubectl delete deployment imgservice-dep


kubectl apply -f gcp-service.yaml -n msa
kubectl delete service imgservice-svc -n msa