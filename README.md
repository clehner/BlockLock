BlockLock
========

BlockLock is a Minecraft plugin that prevents people from building or breaking
blocks at locations where another player has built. It is like OwnBlocks but is
all-or-nothing (players cannot choose to have only certain blocks protected).
It requires nothing more than LogBlock.

The intended use case is to prevent untrusted/guest players from destroying the
work of other players, but to still allow them to get started playing. Once they
gain the trust of the community, then they could be given the permission to
change other people's blocks, to allow for collaboration.

Permissions
-----------

* `blocklock.buildanywhere`
	allows a player to break blocks placed by other players,
	and place blocks in places cleared by other players.

Configuration
-------------

None. ;)

Compiling
---------

    ant
