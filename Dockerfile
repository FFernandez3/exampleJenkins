FROM jenkins/jenkins:2.489-jdk17
USER root

# Tus instalaciones actuales
RUN apt-get update && apt-get install -y lsb-release
RUN curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc \
  https://download.docker.com/linux/debian/gpg
RUN echo "deb [arch=$(dpkg --print-architecture) \
  signed-by=/usr/share/keyrings/docker-archive-keyring.asc] \
  https://download.docker.com/linux/debian \
  $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list
RUN apt-get update && apt-get install -y docker-ce-cli maven

# Agregar configuración de permisos
RUN groupadd -f docker
RUN usermod -aG docker jenkins
RUN chmod 666 /var/run/docker.sock || true

USER jenkins

RUN jenkins-plugin-cli --plugins "blueocean:1.27.16 docker-workflow:580.vc0c340686b_54"