aws ecr get-login-password --region us-east-1 --profile comfama_dev | docker login --username AWS --password-stdin 749892430062.dkr.ecr.us-east-1.amazonaws.com
docker build --no-cache --tag=749892430062.dkr.ecr.us-east-1.amazonaws.com/comfama/mapeo-cosmosdb-kafka:latest .
docker push 749892430062.dkr.ecr.us-east-1.amazonaws.com/comfama/mapeo-cosmosdb-kafka:latest