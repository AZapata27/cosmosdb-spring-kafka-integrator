import logging
from confluent_kafka import Consumer, KafkaError
import pandas as pd
import os

# Configuración de logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

# Configuración del consumidor de Kafka
conf = {
    'bootstrap.servers': os.getenv('KAFKA_BOOTSTRAP_SERVERS'),
    'group.id': os.getenv('KAFKA_GROUP_ID'),
    'auto.offset.reset': os.getenv('KAFKA_AUTO_OFFSET_RESET', 'earliest'),
    'security.protocol': os.getenv('KAFKA_SECURITY_PROTOCOL', 'SASL_SSL'),
    'sasl.mechanisms': os.getenv('KAFKA_SASL_MECHANISMS', 'SCRAM-SHA-512'),
    'sasl.username': os.getenv('KAFKA_SASL_USERNAME'),
    'sasl.password': os.getenv('KAFKA_SASL_PASSWORD')
}

# Crear el consumidor
consumer = Consumer(conf)
logging.info('Consumidor de Kafka creado.')

# Suscribirse al tópico
topic = 'ZSUB_LEGALREP_DT'  # Reemplaza 'your_topic' por el nombre de tu tópico
consumer.subscribe([topic])
logging.info(f'Suscrito al tópico: {topic}')

# Crear una lista para almacenar las claves
keys = []

try:
    while True:
        msg = consumer.poll(timeout=1.0)  # Espera por mensajes

        if msg is None:
            continue
        if msg.error():
            if msg.error().code() == KafkaError._PARTITION_EOF:
                # Fin de la partición
                logging.info('Fin de la partición alcanzado.')
                continue
            else:
                logging.error(f'Error en el mensaje: {msg.error()}')
                break

        # Extraer la clave del mensaje
        key = msg.key()
        if key:
            key_decoded = key.decode('utf-8')
            keys.append(key_decoded)
            logging.info(f'Clave recibida: {key_decoded}')

except KeyboardInterrupt:
    logging.info('Proceso interrumpido por el usuario.')
finally:
    # Cerrar el consumidor
    consumer.close()
    logging.info('Consumidor cerrado.')

logging.info(f'Se han recopilado {len(keys)} claves.')
# Crear un DataFrame de pandas con las claves
df = pd.DataFrame(keys, columns=['key'])
logging.info(f'Muestra de claves recopiladas: {keys[:10]}')

# Verificar permisos de escritura
try:
    with open('test_write.csv', 'w') as f:
        f.write('test')
    logging.info('Escritura de prueba exitosa.')
except IOError as e:
    logging.error(f'Error de escritura: {e}')

# Exportar las claves a un archivo CSV
try:
    df.to_csv('kafka_keys.csv', index=False)
    logging.info('Las claves se han exportado a kafka_keys.csv.')
except Exception as e:
    logging.error(f'Error al exportar a CSV: {e}')
