import logging
from azure.cosmos import CosmosClient
import pandas as pd
import os

# Configuración de logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

# Configuración de conexión a Cosmos DB
url = os.getenv('COSMOSDB_URL')
key = os.getenv('COSMOSDB_KEY')
database_name = os.getenv('COSMOSDB_DATABASE_NAME')
container_name = os.getenv('COSMOSDB_CONTAINER_NAME')  # Nombre del contenedor

# Crear el cliente de Cosmos DB
logging.info('Creando el cliente de Cosmos DB...')
client = CosmosClient(url, key)
database = client.get_database_client(database_name)
container = database.get_container_client(container_name)
logging.info('Cliente de Cosmos DB creado y contenedor seleccionado.')

# Definir la consulta
query = 'SELECT * FROM c'  # Ajusta la consulta según tus necesidades
logging.info('Consulta definida: %s', query)

# Ejecutar la consulta y obtener todos los registros
logging.info('Ejecutando la consulta...')
items = list(container.query_items(
    query=query,
    enable_cross_partition_query=True
))
logging.info('Consulta ejecutada. Número de elementos recuperados: %d', len(items))

# Verificar los primeros 5 elementos (opcional)
logging.info('Primeros 5 elementos recuperados: %s', items[:5])

# Convertir los resultados en un DataFrame de pandas
logging.info('Convirtiendo los resultados a un DataFrame de pandas...')
df = pd.DataFrame(items)
logging.info('Conversión completada.')

# Exportar los datos a un archivo CSV
csv_filename = 'cosmos_db_data.csv'
logging.info('Exportando los datos a %s...', csv_filename)
df.to_csv(csv_filename, index=False)
logging.info('Exportación completada. Datos guardados en %s.', csv_filename)










































































