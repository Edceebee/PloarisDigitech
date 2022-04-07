>## ASSESSMENT 

>#### Post Endpoint `/api/v1/box ` can be used to register a new box to the database.
>##### parameters needed for endpoint
> + BoxDto (body containing fields needed for a box to be created)

>#### Post Endpoint `/api/v1/item/{boxId} ` can be used to add an item to a box and save to the database.
>##### parameters needed for endpoint
>+ ItemDto (body containing fields needed for a item to be added to a box)
>+ boxId (used to know which box the item wants to be added to)

>#### Get Endpoint `/api/v1/item/{boxId} ` can be used to check the items in a box.
>##### parameters needed for endpoint
>+ boxId (used to know which of the boxes the items in it wants to be seen)

>#### Get Endpoint `/api/v1/boxes ` can be used to check all the boxes in the database that are reading for loading.

>#### Post Endpoint `/api/v1//batteryCapacity/{boxId}} ` can be used to check the battery percent of a box.
>##### parameters needed for endpoint
>+ boxId (used to know which box you want to know the battery percentage)






# PloarisDigitech
