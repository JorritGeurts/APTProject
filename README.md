# APTProject - Belgische Regering 2020-2024

Wij hebben voor onze opdracht een applicatie ontwikkeld voor de Belgische Regering van 2020-2024. Onze applicatie heeft 4 micro-services geïmplementeerd die de nodige functionaliteiten aanbied. In onze applicatie kunnen de verschillende partijen, regeringen, ministers en ook natuurlijk alle leden worden gemanaged. Elke micro-service bied de benodigde functionaliteiten aan om alles succesvol te laten verlopen. Alles loopt dan via een API-Gateway die alle routing handelt. Iedere persoon kan op onze applicatie komen om alles te bekijken, maar enkel de admins kunnen de data aanpassen. Wij maken gebruik van OAuth beveliging om de data veilig en integer te houden.

![API-Belgische-Regering drawio (2)](https://github.com/user-attachments/assets/56da6fa9-d002-47f6-8600-2db6da746b37)

## CheckList:
- 4 Microservices✅
- CI/CD Deployment✅
- Docker Compose Deployment✅
- Beveiliging✅
- Unit Testing✅

## Extra's:
- Front-end in container✅

## Microservices:
- API-Gateway : 8083 (Handeled alle routing naar de andere services)
- Partijlid-Service : 8081 (De hoofd-service, heeft alle personen die in deze applicatie voorkomen)
- Partij-Service : 8082 (Een service met alle verschillende partijen)
- Regering-Service : 8080 (Een service met alle verschillende regeringen)
- Minister-Service : 8084 (Een service met verschillende ministers)

- Front End : 4200

## API Endpoints
Partijlid
- "/all": geeft alle personenen
- "/{id}": geeft de persoon met een specifieke id
- "/naam/{naam}": geeft de persson met een specifieke naam
- "/create": maakt een nieuwe persoon aan
- "/{id}/edit": bewerkt de data van een bepaalde id
- "/{id}/delete": verwijderd een bepaalde id

Partijen
- "/alle-partijen": geeft alle partijen
- "/partij/{naam}: geeft de partij met een bepaalde naam

Regeringen
- "/alle-regeringen": geeft alle regeringen
- "/regering/{naam}": geeft de regering met een bepaalde naam

Ministers
- "/all": geeft alle ministers
- "/{id}": geeft de minister met een specifieke id
- "/create": maakt een nieuwe minister aan
- "/{id}/edit": bewerkt de data van een bepaalde id
- "/{id}/delete": verwijderd een bepaalde id

## POSTMAN Endpoints
Algemene endpoints

- alle leden
![Alt Text](Images/Alle-Leden.png)

- lid met specifieke id
![Alt Text](Images/LidId.png)

- lid met een specifieke naam
![Alt Text](Images/PartijlidNaam.png)

- create van een partijlid
![Alt Text](Images/CreatingLid.png)

- edit van een partijlid
![Alt Text](Images/EditLid.png)

- delete van een partijlid
![Alt Text](Images/DeleteLid.png)

- alle partijen
![Alt Text](Images/Alle-Partijen.png)

- partij bij naam
![Alt Text](Images/PartijNaam.png)

- alle regeringen
![Alt Text](Images/Alle-Regeringen.png)

- regering bij naam
![Alt Text](Images/RegeringNaam.png)

- alle ministers
![Alt Text](Images/Alle-Ministers.png)

- minster met specifieke id
![Alt Text](Images/MinisterId.png)

- minister met een specifieke naam van functie (minister naam, niet persoon)
![Alt Text](Images/MinisterNaam.png)

- create van een minister
![Alt Text](Images/MinisterCreate.png)

- edit van een minister
![Alt Text](Images/MinisterEdit.png)

- delete van een minister
![Alt Text](Images/MinisterDelete.png)

- unauthorized
![Alt Text](Images/Unauthorized.png)
