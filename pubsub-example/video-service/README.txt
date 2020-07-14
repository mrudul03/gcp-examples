
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

https://cloud.google.com/video-intelligence/docs/reference/rpc/google.cloud.videointelligence.v1#google.cloud.videointelligence.v1.Feature

docker build -t video-service:v01 .
docker tag video-service:v01 gcr.io/msa-gke-demo/video-service:v01
gcloud auth configure-docker
docker push gcr.io/msa-gke-demo/video-service:v01

kubectl get namespaces
kubectl apply -f gcp-deployment.yaml

kubectl get pods
kubectl logs videoservice-dep-6b4fcbb866-8q8df

kubectl describe pod videoservice-dep-6b4fcbb866-8q8df

kubectl delete deployment videoservice-dep



